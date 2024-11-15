from sqlalchemy import create_engine, Column, String, Integer, Date, ForeignKey
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, relationship
import os
import pandas as pd
import pymysql

Base = declarative_base()

class Etudiant(Base):
    __tablename__ = 'etudiant'
    id = Column(Integer, primary_key=True)
    codeEtudiant = Column(String)
    prenom = Column(String)
    nom = Column(String)
    dateNaissance = Column(Date)
    email = Column(String)
    telephone = Column(String)
    cours = relationship("CoursEtudiant", back_populates="etudiant")


class Cours(Base):
    __tablename__ = 'cours'
    id = Column(Integer, primary_key=True)
    nom = Column(String)
    etudiants = relationship("CoursEtudiant", back_populates="cours")
