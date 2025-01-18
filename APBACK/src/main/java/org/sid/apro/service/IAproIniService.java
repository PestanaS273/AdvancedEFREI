package org.sid.apro.service;

import org.sid.apro.entities.*;
import org.sid.apro.vo.FormeVO;
import org.sid.apro.vo.NewFormeVO;
import org.sid.apro.vo.ReponseVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IAproIniService {

    public Utilisateur saveUtilisateur(String email, String password, String role);

    public Etudiant createEtudiant(String email, String password, String role);

    public Role saveRole(Role role);

    public Utilisateur getUtilisateurByEmail(String email);

    public void addRoleToUser(String email, String role);

    public void inscrireAdmin(String email);

    public List<Utilisateur> getAllUtilisateurs();

    public boolean CheckEmailExist(String email);

    public List<Utilisateur> allUtilisateurs();

    public List<Etudiant> allEtudiants();

    public List<Admin> allAdmins();

    public List<Intervenant> allIntervenants();

    public Admin getAdminByEmail(String email);

    public Etudiant getEtudiantByEmail(String email);

    public Intervenant getIntervenantByEmail(String email);

    public List<Question> allQuestions(Cours cours);

    public List<Cours> allCours();

    public void initCours();

   // public int initProf();

    public void initQuestions();

    public int initFormes();


    public Reponse saveReponse(ReponseVO reponseVO);

    public int NombreEtudiantsActif();

    public List<Cours> getAllCours(long idIntervenant);

    public List<Cours> getAllCoursEtudiants(long idEtudiant);

    public List<Forme> getAllForme(long idCours, long idEtudiant);

    public ArrayList<Forme> saveQuestion(FormeVO formeVO);

    public Etudiant getEtudiantFromResponse(long idForme);

    public Forme createForme(NewFormeVO newFormeVO);

    public List<Etudiant> getEtudiantsFromCours(long idCours);

    public List<Forme> getAllFroms();

    public List<Forme> getAllFromsFromCours(long idCours);

    public List<Intervenant> getAllIntervenants();

    public Forme getFormeById(long idForme);

    public List<Forme> getAllFormesFromEtudiant(long idEtudiant);


}
