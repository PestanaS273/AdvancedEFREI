from sqlalchemy import create_engine, Column, String, Integer, Date, ForeignKey, BigInteger, Boolean
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship

Base = declarative_base()

class Utilisateur(Base):
    __tablename__ = 'utilisateur'
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    date_naissance = Column(Date)
    statut = Column(Boolean)
    email = Column(String(255))
    image_profile = Column(String)
    nom = Column(String(255))
    num_tel = Column(String(20))
    password = Column(String)
    prenom = Column(String(255))
    etudiant = relationship("Etudiant", back_populates="utilisateur", cascade="all, delete-orphan", uselist=False)

class Etudiant(Base):
    __tablename__ = 'etudiant'
    id = Column(BigInteger, ForeignKey('utilisateur.id'), primary_key=True)
    num_etudiant = Column(BigInteger, nullable=False)
    etat = Column(Boolean)
    utilisateur = relationship("Utilisateur", back_populates="etudiant")
    cours = relationship("EtudiantCours", back_populates="etudiant", cascade="all, delete-orphan")

class Cours(Base):
    __tablename__ = 'cours'
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    nom_cours = Column(String(255), nullable=False, unique=True)
    forme_id = Column(BigInteger, ForeignKey('forme.id'), nullable=True)
    etudiants = relationship("EtudiantCours", back_populates="cours")
    forme = relationship("Forme", back_populates="cours")


class EtudiantCours(Base):
    __tablename__ = 'etudiant_cours'
    etudiant_id = Column(BigInteger, ForeignKey('etudiant.id'), primary_key=True)
    cours_id = Column(BigInteger, ForeignKey('cours.id'), primary_key=True)
    etudiant = relationship("Etudiant", back_populates="cours")
    cours = relationship("Cours", back_populates="etudiants")


class Forme(Base):
    __tablename__ = 'forme'
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    question_id = Column(BigInteger, ForeignKey('question.id'))
    reponse_id = Column(BigInteger, ForeignKey('reponse.id'))
    cours = relationship("Cours", back_populates="forme")
