package org.sid.apro.service;

import jakarta.transaction.Transactional;
import org.sid.apro.dao.*;
import org.sid.apro.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;


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
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private ReponseRepository reponseRepository;
    @Autowired
    private IntervenantRepository intervenantRepository;
    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public Utilisateur saveUtilisateur(String email, String password) {
//        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
//        if(utilisateur != null) throw new RuntimeException("Utilisateur exist");
//        Utilisateur newUtilisateur = new Utilisateur();
//        newUtilisateur.setEmail(email);
        Utilisateur newUtilisateur =  utilisateurRepository.findByEmail(email);
        newUtilisateur.setPassword(bCryptPasswordEncoder.encode(password));
        newUtilisateur.setStatut(true);
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

    @Override
    public void inscrireAdmin(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if(utilisateur != null) throw new RuntimeException("Utilisateur exist");
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setEmail(email);
        newUtilisateur.setStatut(true);
        utilisateurRepository.save(newUtilisateur);
        addRoleToUser(email,"admin");

    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs;
    }

    @Override
    public boolean CheckEmailExist(String email) {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if(user == null) return false;
        return true;
    }

    @Override
    public List<Utilisateur> allUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs;
    }

    @Override
    public List<Etudiant> allEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return etudiants;
    }

    @Override
    public List<Admin> allAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins;
    }

    @Override
    public List<Intervenant> allIntervenants() {
        List<Intervenant> intervenants = intervenantRepository.findAll();
        return intervenants;
    }

    @Override
    public Admin getAdminByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email);
        if(admin == null) throw new RuntimeException("Utilisateur n'exist pas");
        return admin;
    }

    @Override
    public Etudiant getEtudiantByEmail(String email) {
        Etudiant etudiant = etudiantRepository.findByEmail(email);
        return etudiant;
    }

    @Override
    public Intervenant getIntervenantByEmail(String email) {
        Intervenant intervenant = intervenantRepository.findByEmail(email);
        return intervenant;
    }

    @Override
    public List<Question> allQuestions(Cours cours) {
        List<Question> questions = questionRepository.findAll();
        return questions;
    }

    @Override
    public List<Cours> allCours() {
        List<Cours> cours = coursRepository.findAll();
        return cours;
    }
}
