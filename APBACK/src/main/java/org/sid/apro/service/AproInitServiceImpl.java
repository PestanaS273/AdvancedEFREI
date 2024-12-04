package org.sid.apro.service;

import jakarta.transaction.Transactional;
import org.sid.apro.dao.*;
import org.sid.apro.entities.*;
import org.sid.apro.vo.ReponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


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
        /*Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if(utilisateur != null) throw new RuntimeException("Utilisateur exist");
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setEmail(email);*/
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
        if(user.isStatut())  throw new RuntimeException("Utilisateur Already Active");
        if(user == null) throw new RuntimeException("Utilisateur null");
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

    @Override
    public void initCours() {
        Stream.of("Spring boot basics", "Advanced JPA", "RESTful APIs", "Microservices Architecture", "Hibernate Deep Dive")
                .forEach(cours -> {
                    Cours newCours = new Cours();
                    newCours.setNomCours(cours);
                    coursRepository.save(newCours);
                });
    }

    @Override
    public void initQuestions() {
        Stream.of("What is Spring Boot?", "Explain JPA relationships.", "How to secure a REST API?",
                        "What is a microservice?", "What are HTTP status codes?")
                .forEach(questionText -> {
                    Question question = new Question();
                    question.setQuestion(questionText);
                    questionRepository.save(question);
                });
    }
/*
    @Override
    public void initFormes() {
        coursRepository.findAll().forEach(cours -> {
            for (int i = 0; i < 3; i++) { // 3 formes par cours
                Forme forme = new Forme();

                // Associer une question aléatoire
                Question question = questionRepository.findById((long) (1 + Math.random() * 5)).orElse(null);
                if (question != null) {
                    forme.setQuestion(question);
                }

                // Associer une réponse aléatoire (optionnel, à modifier selon votre structure)
                Reponse reponse = reponseRepository.findById((long) (1 + Math.random() * 5)).orElse(null);
                if (reponse != null) {
                    forme.setReponse(reponse);
                }

                formRepository.save(forme);
                cours.getFormes().add(forme); // Associer la forme au cours
            }
            coursRepository.save(cours); // Sauvegarder le cours avec ses formes
        });
    }
*/
    @Override
    public Reponse saveReponse(ReponseVO reponseVO) {
        return null;
    }

    @Override
    public int NombreEtudiantsActif() {
        return 0;
    }

    @Override
    public Cours getAllCours(long idIntervenant) {
        return null;
    }

    @Override
    public Cours getAllCoursEtudiants(long idEtudiant) {
        return null;
    }

    @Override
    public Forme getAllForme(long id) {
        return null;
    }


}
