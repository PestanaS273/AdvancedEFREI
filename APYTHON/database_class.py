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
    intervenant = relationship("Intervenant", back_populates="utilisateur", cascade="all, delete-orphan", uselist=False)
    roles = relationship("UtilisateurRoles", back_populates="utilisateur", cascade="all, delete-orphan")

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
    etudiants = relationship("EtudiantCours", back_populates="cours")
    intervenants = relationship("IntervenantCours", back_populates="cours")
    formes = relationship("CoursFormes", back_populates="cours")

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
    cours = relationship("CoursFormes", back_populates="forme")

class Intervenant(Base):
    __tablename__ = 'intervenant'
    id = Column(BigInteger, ForeignKey('utilisateur.id'), primary_key=True)
    specialiste = Column(String(255), nullable=True)
    utilisateur = relationship("Utilisateur", back_populates="intervenant", uselist=False)
    cours = relationship("IntervenantCours", back_populates="intervenant", cascade="all, delete-orphan")

class IntervenantCours(Base):
    __tablename__ = 'intervenant_cours'
    intervenant_id = Column(BigInteger, ForeignKey('intervenant.id'), primary_key=True)
    cours_id = Column(BigInteger, ForeignKey('cours.id'), primary_key=True)
    intervenant = relationship("Intervenant", back_populates="cours")
    cours = relationship("Cours", back_populates="intervenants")


class Role(Base):
    __tablename__ = 'role'
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    role = Column(String(255), nullable=False, unique=True)
    utilisateurs = relationship("UtilisateurRoles", back_populates="role")

class UtilisateurRoles(Base):
    __tablename__ = 'utilisateur_roles'
    utilisateur_id = Column(BigInteger, ForeignKey('utilisateur.id'), primary_key=True)
    roles_id = Column(BigInteger, ForeignKey('role.id'), primary_key=True)
    utilisateur = relationship("Utilisateur", back_populates="roles")
    role = relationship("Role", back_populates="utilisateurs")

class CoursFormes(Base):
    __tablename__ = 'cours_formes'
    cours_id = Column(BigInteger, ForeignKey('cours.id'), primary_key=True)
    forme_id = Column(BigInteger, ForeignKey('forme.id'), primary_key=True)
    cours = relationship("Cours", back_populates="formes")
    forme = relationship("Forme", back_populates="cours")

