package org.sid.apro.service;

import jakarta.transaction.Transactional;
import org.sid.apro.dao.RoleRepository;
import org.sid.apro.dao.UtilisateurRepository;
import org.sid.apro.entities.Role;
import org.sid.apro.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
@Transactional
@CrossOrigin("*")
public class AproInitServiceImpl implements IAproIniService{

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Utilisateur saveUtilisateur(String email, String password, String confirmedPaswword) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if(utilisateur != null) throw new RuntimeException("Utilisateur exist");
        if(!password.equals(confirmedPaswword)) throw new RuntimeException("Passwords do not match");
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setEmail(email);
        newUtilisateur.setPassword(bCryptPasswordEncoder.encode(password));
        utilisateurRepository.save(newUtilisateur);
        addRoleToUser(email,"etudiant");

        return newUtilisateur;
    }

    @Override
    public Role saveRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public void addRoleToUser(String email, String role) {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        Role roles = roleRepository.findByRole(role);
        user.getRoles().add(roles);
    }
}
