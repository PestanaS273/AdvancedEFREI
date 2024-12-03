package org.sid.apro.service;

import org.sid.apro.entities.*;

import java.util.List;
import java.util.Optional;

public interface IAproIniService {

    public Utilisateur saveUtilisateur(String email, String password);
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







}
