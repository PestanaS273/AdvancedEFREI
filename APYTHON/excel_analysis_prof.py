import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
import os
from sqlalchemy.exc import SQLAlchemyError

from dotenv import load_dotenv
from database_class import Base, Utilisateur, Cours, Intervenant, Role, IntervenantCours, UtilisateurRoles
import pymysql

import logging



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

    teacher_array = []
    cours_teacher = []

    progress_status["message"] = "Processing Data"
    for index, row in df.iterrows():
        cours_teacher = []
        if "coursNom" in row and isinstance(row["coursNom"], str) and "," in row["coursNom"]:
            for cour in row["coursNom"].split(","):
                cours_teacher.append({
                    "email": row["email"],
                    "coursNom": cour
                })
        elif "coursNom" in row:
            cours_teacher.append({
                "email": row["email"],
                "coursNom": row["coursNom"]
            })

        teacher = {
            "specialiste": row["specialiste"],
            "prenom": row["prenom"],
            "nom": row["nom"],
            "date_naissance": row["dateNaissance"],
            "email": row["email"],
            "num_tel": row["telephone"],
            "cours": cours_teacher
        }

        teacher_array.append(teacher)
        progress_status["percentage"] = 20 + int((index + 1) / len(df) * 40)


    print("Teacher array:", teacher_array)
    update_database(teacher_array)

def update_database(teacher_array):
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

    total_teacher = len(teacher_array)
    processed_teacher = 0

    try:
        teacher_role = session.query(Role).filter_by(role='intervenant').first()
        if not teacher_role:
            teacher_role = Role(
                role='intervenant'
            )
            session.add(teacher_role)
            session.flush()

        teacher_role_id = teacher_role.id
        print("Teacher Role ID:", teacher_role_id)

        for teacher_data in teacher_array:
            print(f"Searching for utilisateur with email: {teacher_data['email']}")
            utilisateur = session.query(Utilisateur).filter(Utilisateur.email == teacher_data["email"]).first()

            if not utilisateur:
                utilisateur = Utilisateur(
                    prenom=teacher_data["prenom"],
                    nom=teacher_data["nom"],
                    email=teacher_data["email"],
                    date_naissance=teacher_data["date_naissance"],
                    num_tel=teacher_data["num_tel"],
                    password="pass202234",
                    statut=False  
                )
                session.add(utilisateur)
                session.flush()

                teacher = Intervenant(
                    id=utilisateur.id,
                    specialiste=teacher_data["specialiste"],
                )
                session.add(teacher)
                session.flush()

                teacher_role = UtilisateurRoles (
                    utilisateur_id=utilisateur.id,
                    roles_id=teacher_role_id
                )
                session.add(teacher_role)
                session.flush()

            else :
                print(f"Updating utilisateur ID: {utilisateur.id}, Email: {utilisateur.email}")
                if utilisateur:
                    utilisateur.prenom = teacher_data["prenom"]
                    utilisateur.nom = teacher_data["nom"]
                    utilisateur.num_tel = teacher_data["num_tel"]
                    utilisateur.statut = utilisateur.password != "pass202234"
                    print("teacher updated")
                    print(vars(utilisateur))
                    print(utilisateur.id)
                    session.flush()
                    

                    teacher = session.query(Intervenant).filter_by(id=utilisateur.id).first()
                    if teacher:
                        teacher.specialiste = teacher_data["specialiste"]

                        print("teacher updated")
                else:
                    print(f"Utilisateur with ID {utilisateur.id} not found for update.")

            print("teacher added")

            session.query(IntervenantCours).filter_by(intervenant_id=utilisateur.id).delete()
            print("updating teacher courses")
            for course_data in teacher_data["cours"]:
                cours = session.query(Cours).filter_by(nom_cours=course_data["coursNom"]).first()
                if not cours:
                    cours = Cours(
                        nom_cours=course_data["coursNom"],
                        forme_id=None
                    )
                    session.add(cours)
                    print("cours added")
                    session.flush() 

                teacher_cours = IntervenantCours(
                    intervenant_id=utilisateur.id,
                    cours_id=cours.id
                )
                session.add(teacher_cours)

            processed_teacher += 1
            progress_status["percentage"] = 60 + int((processed_teacher / total_teacher) * 40)

        session.commit()

    # except Exception as e:
    #     print(f"Error processing teacher {teacher_data.get('email', 'Unknown')}: {e}")
    #     session.rollback()
    except pymysql.IntegrityError as e:
        print(f"Database integrity error: {e}")
    except SQLAlchemyError as e:
        print(f"SQLAlchemy error: {e}")
    
    finally:
        print("Database update complete")
        progress_status["step"] = 3
        progress_status["message"] = "Analysis Complete"
        progress_status["percentage"] = 100
        session.close()