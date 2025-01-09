package org.sid.apro.service;

import jakarta.transaction.Transactional;
import org.sid.apro.dao.*;
import org.sid.apro.entities.*;
import org.sid.apro.vo.FormeVO;
import org.sid.apro.vo.NewFormeVO;
import org.sid.apro.vo.ReponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
@Transactional
@CrossOrigin("*")
public class AproInitServiceImpl implements IAproIniService {

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
    @Autowired
    private QuestionReponseRepository questionReponseRepository;


    @Override
    public Utilisateur saveUtilisateur(String email, String password, String role) {
        // Vérifiez si l'utilisateur existe déjà par son email
        Utilisateur existingUtilisateur = utilisateurRepository.findByEmail(email);
        if (role != null && role.equalsIgnoreCase("etudiant")) {
            Etudiant etudiant = new Etudiant();
            etudiant.setEmail(email);
            etudiant.setPassword(bCryptPasswordEncoder.encode(password));
            etudiant.setStatut(true);
            etudiant.setEtat(true); // Par défaut, actif
            //etudiant.setNumEtudiant(utilisateur.getId());
            // Sauvegarder l'étudiant
            etudiantRepository.save(etudiant);
            addRoleToUser(email, "etudiant");
            return etudiant;
        } else {
            Admin admin = adminRepository.findByEmail(email);
            if (admin != null) throw new RuntimeException("Admin exist");
            Admin newAdmin = new Admin();
            newAdmin.setEmail(email);
            newAdmin.setStatut(true);
            utilisateurRepository.save(newAdmin);
            addRoleToUser(email, "admin");
            return newAdmin;
        }
    }

    @Override
    public Etudiant createEtudiant(String email, String password, String role) {
        // Créer l'entité Étudiant
        Etudiant etudiant = etudiantRepository.findByEmail(email);
        //etudiant.setEmail(email);
        etudiant.setPassword(bCryptPasswordEncoder.encode(password));
        etudiant.setStatut(true);
        etudiant.setEtat(true); // Par défaut, actif
        //etudiant.setNumEtudiant(utilisateur.getId());
        // Sauvegarder l'étudiant
        etudiantRepository.save(etudiant);
        //addRoleToUser(email, "etudiant");
        return etudiant;
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
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) throw new RuntimeException("Admin exist");
        Admin newAdmin = new Admin();
        newAdmin.setEmail(email);
        newAdmin.setStatut(true);
        utilisateurRepository.save(newAdmin);
        addRoleToUser(email, "admin");

    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs;
    }

    @Override
    public boolean CheckEmailExist(String email) {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user == null) throw new RuntimeException("Utilisateur null");
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
        if (admin == null) throw new RuntimeException("Utilisateur n'exist pas");
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

    @Transactional
    @Override
    public void initCours() {
        Stream.of("Spring boot basics", "Advanced JPA", "RESTful APIs", "Microservices Architecture", "Hibernate Deep Dive")
                .forEach(coursName -> {
                    // Créer et sauvegarder un cours
                    Cours newCours = new Cours();
                    newCours.setNomCours(coursName);
                    coursRepository.save(newCours);

                    // Rechercher l'étudiant dans la base
                    etudiantRepository.findAll().forEach(etudiant -> {
                        if (etudiant != null) {
                            // Ajouter le cours à l'étudiant et vice-versa
                            etudiant.getCours().add(newCours);
                            newCours.getEtudiants().add(etudiant);

                            // Sauvegarder les deux entités
                            utilisateurRepository.save(etudiant);
                            coursRepository.save(newCours);
                        } else {
                            throw new RuntimeException("Etudiant not found");
                        }
                    });
                });

    }
/*
    @Override
    public int initProf() {
        Stream.of("Clément@gmail.com", "Mathieu@gmail.com", "Arabi@gmail.com", "Saouane@gmail.com")
                .forEach(profName -> {
                    int i = 0;
                    Utilisateur user = new Utilisateur();
                    user.setEmail(profName);
                    utilisateurRepository.save(user);
                    Intervenant intervenant = new Intervenant();
                    intervenant.setEmail(profName);
                    intervenant.getCours().add(coursRepository.findById(i));
                    intervenantRepository.save(intervenant);
                    addRoleToUser(profName, "professor");
                    i++;
                });
        return 0;
    }
    */


    @Override
    public void initQuestions() {
        Stream.of("What is Spring Boot?", "Explain JPA relationships.", "How to secure a REST API?",
                        "What is a microservice?", "What are HTTP status codes?")
                .forEach(questionText -> {
                    QuestionReponse question = new QuestionReponse();
                    question.setQuestion(questionText);
                    question.setReponse(null);
                    questionReponseRepository.save(question);
                });
    }

    @Override
    public int initFormes() {
        coursRepository.findAll().forEach(cours -> {
            for (int i = 0; i < 3; i++) { // 3 formes par cours
                Forme forme = new Forme();
/*
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
*/
                QuestionReponse questionReponse = questionReponseRepository.findByQuestionId((long) (1 + Math.random() * 5));
                forme.getQuestionReponses().add(questionReponse);
                formRepository.save(forme);
                cours.getFormes().add(forme);
            }
            coursRepository.save(cours);
        });
        return 1;
    }

    @Override
    public Reponse saveReponse(ReponseVO reponseVO) {
        return null;
    }

    @Override
    public int NombreEtudiantsActif() {
        List<Utilisateur> etudiantsActive = new ArrayList<>();
        int c = 0;
        for (Utilisateur e : utilisateurRepository.findAll()) {
            System.out.println("mon c'est le role :" + e.getRoles());
            Role role = e.getRoles().iterator().next();
            String r = role.getRole();
            System.out.println(r);
            if (r.equals("etudiant")) {
                System.out.println(e.isStatut());
                if (e.isStatut()) {
                    etudiantsActive.add(e);
                }
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
            if (idCours == cours.getId()) {
                cours.getFormes().forEach(forme -> {
                    formes.add(forme);
                });
            }
        });
        return formes;
    }

    @Override
    public ArrayList<Forme> saveQuestion(FormeVO formeVO) {
        Etudiant etudiant = etudiantRepository.findById(formeVO.getIdEtudiant());
        // tous les cours d'un étudiant!
        ArrayList<Cours> cours = (ArrayList<Cours>) getAllCoursEtudiants(formeVO.getIdEtudiant());
        //cours.forEach(cour -> {
        /*
        Question question = questionRepository.findByQuestion(formeVO.getQuestion());
        System.out.println("la question est: " + question.getQuestion());
        long idQuestion = question.getId();
        System.out.println("l'id de la question: " + idQuestion);
        Reponse reponse = new Reponse();
        reponse.setReponse(formeVO.getReponse());
        Forme forme = formRepository.findByIdAndQuestion(formeVO.getIdForme(), idQuestion);
        if (forme == null) throw new RuntimeException("Forme null");
        forme.setEtudiant(etudiant);
        forme.setReponse(reponse);
        formRepository.save(forme);
        //});*/
        QuestionReponse questionReponse = new QuestionReponse();
        questionReponse.setQuestion(formeVO.getQuestion());
        questionReponse.setReponse(formeVO.getReponse());
        questionReponseRepository.save(questionReponse);
        Forme forme = formRepository.findById(formeVO.getIdForme());
        forme.setEtudiant(etudiant);
        forme.getQuestionReponses().add(questionReponse);
        formRepository.save(forme);
        return null;
    }


    @Override
    public Etudiant getEtudiantFromResponse(long idForme) {
        /*
        Reponse reponse = reponseRepository.findById(idResponse).orElse(null);
        if (reponse != null) {
            List<Forme> formes = reponse.getFormes().stream().filter(forme -> idForme == forme.getId()).toList();
            if (formes != null && formes.size() > 0) {
                Forme forme = formes.get(0);
                return forme.getEtudiant();
            }
        }
        */
        Forme forme = formRepository.findById(idForme);
        if (forme == null) throw new RuntimeException("Forme not found");


        return forme.getEtudiant();
    }

    @Override
    public Forme createForme(NewFormeVO newFormeVO) {
        ArrayList<String> questions = (ArrayList<String>) newFormeVO.getQuestions();
        Forme forme = new Forme();
        questions.forEach(question -> {
            QuestionReponse questionReponse = new QuestionReponse();
            questionReponse.setQuestion(question);
            questionReponse.setReponse(null);
            questionReponseRepository.save(questionReponse);
            forme.getQuestionReponses().add(questionReponse);
        });
        formRepository.save(forme);
        return forme;

    }

    @Override
    public List<Etudiant> getEtudiantsFromCours(long idCours) {
        Cours cours = coursRepository.findById(idCours);
        List<Etudiant> etudiants = new ArrayList<>();
        cours.getEtudiants().forEach(etudiant -> {
            etudiants.add(etudiant);
        });
        return etudiants;
    }

    @Override
    public List<Forme> getAllFroms() {
        List<Forme> formes = new ArrayList<>();
        formRepository.findAll().forEach(forme -> {
            formes.add(forme);
        });
        return formes;
    }

    @Override
    public List<Forme> getAllFromsFromCours(long idCours) {
        Cours cours = coursRepository.findById(idCours);
        List<Forme> formes = new ArrayList<>();
        cours.getFormes().forEach(forme -> {
            formes.add(forme);
        });
        return formes;
    }


}
