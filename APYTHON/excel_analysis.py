import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
import os

from dotenv import load_dotenv
from database_class import Base, Utilisateur, Etudiant, Cours, EtudiantCours
import pymysql

load_dotenv()

progress_status = {
    "step": 0,
    "total_steps": 3,
    "percentage": 0,
    "message": ""
}

def get_status():
    return progress_status

def run_analysis(file_path):
    progress_status["step"] = 1
    progress_status["message"] = "Reading Excel File"
    print("Reading Excel File:", file_path)
    df = pd.read_excel(file_path)

    progress_status["percentage"] = 20  

    student_array = []
    cours_etudiant = []

    progress_status["message"] = "Processing Data"
    for index, row in df.iterrows():
        cours_etudiant = []
        if "coursNom" in row and isinstance(row["coursNom"], str) and "," in row["coursNom"]:
            for cour in row["coursNom"].split(","):
                cours_etudiant.append({
                    "num_etudiant": row["codeEtudiant"],
                    "coursNom": cour
                })
        elif "coursNom" in row:
            cours_etudiant.append({
                "num_etudiant": row["codeEtudiant"],
                "coursNom": row["coursNom"]
            })

        student = {
            "num_etudiant": row["codeEtudiant"],
            "prenom": row["prenom"],
            "nom": row["nom"],
            "date_naissance": row["dateNaissance"],
            "email": row["email"],
            "num_tel": row["telephone"],
            "cours": cours_etudiant
        }

        student_array.append(student)
        progress_status["percentage"] = 20 + int((index + 1) / len(df) * 40)


    print("Student array:", student_array)
    update_database(student_array)

def update_database(student_array):
    progress_status["step"] = 2
    progress_status["message"] = "Updating Database"
    print("Updating Database")

    user = os.getenv('DB_USER')
    password = os.getenv('DB_PASSWORD')
    host = os.getenv('DB_HOST')
    database = os.getenv('DB_NAME')
    port = int(os.getenv('DB_PORT'))

    connection = pymysql.connect(
        user=user,
        password=password,
        host=host,
        database=database,
        port=port
    )

    engine = create_engine('mysql+pymysql://', creator=lambda: connection)
    Base.metadata.create_all(engine)
    Session = sessionmaker(bind=engine)
    session = Session()

    total_students = len(student_array)
    processed_students = 0

    try:
        for student_data in student_array:
            etudiant = session.query(Etudiant).filter_by(num_etudiant=student_data["num_etudiant"]).first()

            if not etudiant:
                utilisateur = Utilisateur(
                    prenom=student_data["prenom"],
                    nom=student_data["nom"],
                    date_naissance=student_data["date_naissance"],
                    email=student_data["email"],
                    num_tel=student_data["num_tel"],
                    password="pass202234",
                    statut=False  
                )
                session.add(utilisateur)
                session.flush()  

                etudiant = Etudiant(
                    id=utilisateur.id,
                    num_etudiant=student_data["num_etudiant"],
                    etat=False if utilisateur.password == "pass202234" else True
                )
                session.add(etudiant)
            else:
                utilisateur = session.query(Utilisateur).filter_by(id=etudiant.id).first()
                if utilisateur:
                    utilisateur.prenom = student_data["prenom"]
                    utilisateur.nom = student_data["nom"]
                    utilisateur.date_naissance = student_data["date_naissance"]
                    utilisateur.email = student_data["email"]
                    utilisateur.num_tel = student_data["num_tel"]
                    utilisateur.statut = utilisateur.password != "pass202234"

            session.query(EtudiantCours).filter_by(etudiant_id=etudiant.id).delete()
            for course_data in student_data["cours"]:
                cours = session.query(Cours).filter_by(nom_cours=course_data["coursNom"]).first()
                if not cours:
                    cours = Cours(
                        nom_cours=course_data["coursNom"],
                        forme_id=None
                    )
                    session.add(cours)
                    session.flush() 

                etudiant_cours = EtudiantCours(
                    etudiant_id=etudiant.id,
                    cours_id=cours.id
                )
                session.add(etudiant_cours)

            processed_students += 1
            progress_status["percentage"] = 60 + int((processed_students / total_students) * 40)

        session.commit()


    except Exception as e:
        print(f"Error processing student {student_data.get('num_etudiant', 'Unknown')}: {e}")
        session.rollback()
    finally:
        print("Database update complete")
        progress_status["step"] = 3
        progress_status["message"] = "Analysis Complete"
        progress_status["percentage"] = 100
        session.close()

