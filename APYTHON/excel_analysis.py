import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
import pandas as pd
import os

from database_class import Base, Etudiant, Cours


from dotenv import load_dotenv
import pymysql


progress_status = {"step": 0, "total_steps": 3}

def get_status():
    return progress_status

def run_analysis(file_path):
    progress_status["step"] = 1
    progress_status["message"] = "Reading Excel File"
    df = pd.read_exce;("file_path")

    student_array = []
    cours_etudiant = []


    for index, row in df.iterrows():

        for cour in row["coursNom"].split(","):
            cours_etudiant.append({
                "codeEtudiant": row["codeEtudiant"],
                "coursNom": cour
            })

        student = {
            "codeEtudiant": row["codeEtudiant"],
            "prenom": row["prenom"],
            "nom": row["nom"],
            "dateNaissance": row["dateNaissance"],
            "email": row["email"],
            "telephone": row["telephone"],
            "cours": cours_etudiant
        }

        student_array.append(student)
    
    update_database(student_array)


def update_database(student_array):
    progress_status["step"] = 2
    progress_status["message"] = "Updating Database"

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

    for student_data in student_array:
        student = session.query(Etudiant).filter_by(codeEtudiant=student["codeEtudiant"]).first()
        if student:
            student.prenom = student_data["prenom"]
            student.nom = student_data["nom"]
            student.dateNaissance = student_data["dateNaissance"]
            student.email = student_data["email"]
            student.telephone = student_data["telephone"]
        else:
            student = Etudiant(
                codeEtudiant=student_data["codeEtudiant"],
                prenom=student_data["prenom"],
                nom=student_data["nom"],
                dateNaissance=student_data["dateNaissance"],
                email=student_data["email"],
                telephone=student_data["telephone"]
            )
            session.add(student)


        session.query(Cours).filter_by(codeEtudiant=student["codeEtudiant"]).delete()
        for cours in student_data["cours"]:
            cours = Cours(
                codeEtudiant=student_data["codeEtudiant"],
                coursNom=cours["coursNom"]
            )
            session.add(cours)

    session.commit()
    session.close()            






