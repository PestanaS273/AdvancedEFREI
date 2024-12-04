package org.sid.apro.service;

import jakarta.transaction.Transactional;
import org.sid.apro.dao.*;
import org.sid.apro.entities.*;
import org.sid.apro.vo.ReponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
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
    public Utilisateur saveUtilisateur(String email, String password, String role) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if(utilisateur != null) throw new RuntimeException("Utilisateur exist");
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setEmail(email);
        //Utilisateur newUtilisateur =  utilisateurRepository.findByEmail(email);
        newUtilisateur.setPassword(bCryptPasswordEncoder.encode(password));
        newUtilisateur.setStatut(true);
        utilisateurRepository.save(newUtilisateur);
        if(role == null){
            addRoleToUser(email,"etudiant");
        }else {
            addRoleToUser(email,role);
        }
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
        if(user.isStatut())  throw new RuntimeException("Utilisateur exist");
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
                    Etudiant etudiant = etudiantRepository.findByEmail("user4@gmail.com");;
                    System.out.println("je suis l'etudiant: "+ etudiant.getNom());
                    if(etudiant != null){
                        etudiant.getCours().add(newCours);
                        etudiantRepository.save(etudiant);
                        coursRepository.save(newCours);
                    } else throw new RuntimeException("etudiant not found");

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

 /*   @Override
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
        List<Utilisateur> etudiantsActive = new ArrayList<>();
        int c = 0;
        for (Utilisateur e : utilisateurRepository.findAll()) {
            System.out.println("mon c'est le role :"+e.getRoles());
            Role role = e.getRoles().iterator().next();
            String r = role.getRole();
            System.out.println(r);
            if(r.equals("etudiant")){
                System.out.println(e.isStatut());
                if(e.isStatut()){etudiantsActive.add(e);}
            }
        }
        c = etudiantsActive.size();
        return c;
        }

    @Override
    public List<Cours> getAllCours(long idIntervenant) {

        return null;
    }

    @Override
    public List<Cours> getAllCoursEtudiants(long idEtudiant) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant);
        List<Cours> cours = new ArrayList<>();
        etudiant.getCours().forEach(cour -> {
            cours.add(cour);
        });
        return cours;
    }

    @Override
    public List<Forme> getAllForme(long idCours, long idEtudiant) {
        List<Forme> formes = new ArrayList<>();
        Etudiant etudiant = etudiantRepository.findById(idEtudiant);
        etudiant.getCours().forEach(cours -> {
            if(idCours == cours.getId()){
                cours.getFormes().forEach(forme -> {
                    formes.add(forme);
                });
            }
        });
        return formes;
    }


}
