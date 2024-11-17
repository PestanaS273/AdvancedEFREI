import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
import os

from dotenv import load_dotenv
from database_class import Base, Utilisateur, Etudiant, Cours, EtudiantCours
import pymysql

# Cargar las variables de entorno desde el archivo .env
load_dotenv()

progress_status = {"step": 0, "total_steps": 3}

def get_status():
    return progress_status

def run_analysis(file_path):
    progress_status["step"] = 1
    progress_status["message"] = "Reading Excel File"
    print("Reading Excel File:", file_path)
    df = pd.read_excel(file_path)

    # Imprimir los nombres de las columnas para depuración
    print("Columnas del DataFrame:", df.columns)

    student_array = []
    cours_etudiant = []

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

    try:
        for student_data in student_array:
            # Validar o crear un usuario
            utilisateur = session.query(Utilisateur).filter_by(id=student_data["num_etudiant"]).first()
            if not utilisateur:
                utilisateur = Utilisateur(
                    id=student_data["num_etudiant"],
                    prenom=student_data["prenom"],
                    nom=student_data["nom"],
                    date_naissance=student_data["date_naissance"],
                    email=student_data["email"],
                    num_tel=student_data["num_tel"],
                    statut=True  # Usuario activo
                )
                session.add(utilisateur)
            else:
                # Actualizar campos existentes
                utilisateur.prenom = student_data["prenom"]
                utilisateur.nom = student_data["nom"]
                utilisateur.date_naissance = student_data["date_naissance"]
                utilisateur.email = student_data["email"]
                utilisateur.num_tel = student_data["num_tel"]

            # Validar o crear estudiante
            etudiant = session.query(Etudiant).filter_by(id=utilisateur.id).first()
            if not etudiant:
                etudiant = Etudiant(
                    id=utilisateur.id,
                    num_etudiant=student_data["num_etudiant"],
                    etat=True
                )
                session.add(etudiant)

            # Actualizar cursos asociados
            session.query(EtudiantCours).filter_by(etudiant_id=etudiant.id).delete()
            for course_data in student_data["cours"]:
                cours = session.query(Cours).filter_by(nom_cours=course_data["coursNom"]).first()
                if not cours:
                    # Crear un nuevo curso si no existe
                    cours = Cours(
                        nom_cours=course_data["coursNom"],
                        forme_id=None  # Si no hay form relacionado
                    )
                    session.add(cours)
                    session.commit()  # Necesario para persistir el curso y obtener su ID

                # Crear la relación entre estudiante y curso
                etudiant_cours = EtudiantCours(
                    etudiant_id=etudiant.id,
                    cours_id=cours.id  # Asegúrate de incluir cours.id
                )
                session.add(etudiant_cours)

        session.commit()
        print("Database update complete")

    except Exception as e:
        session.rollback()
        print(f"Error during update: {e}")
    finally:
        session.close()
