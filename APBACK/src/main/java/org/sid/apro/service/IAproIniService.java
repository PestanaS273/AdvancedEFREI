package org.sid.apro.service;

import org.sid.apro.entities.Role;
import org.sid.apro.entities.Utilisateur;

import java.util.List;

public interface IAproIniService {

    public Utilisateur saveUtilisateur(String email, String password, String confirmedPaswword);
    public Role saveRole(Role role);
    public Utilisateur getUtilisateurByEmail(String email);
    public void addRoleToUser(String email, String role);
    public List<Utilisateur> getAllUtilisateurs();




}
