package org.sid.apro.web;

import jakarta.transaction.Transactional;
import org.sid.apro.entities.*;
import org.sid.apro.service.IAproIniService;
import org.sid.apro.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@CrossOrigin("*")
public class ApoRestController {

    @Autowired
    private IAproIniService service;
    @PostMapping("/inscrire")
    public Utilisateur saveUser(@RequestBody User user) {
        return service.saveUtilisateur(user.getEmail(), user.getPassword()) ;
    }
    @GetMapping("/users")
    public List<Utilisateur> getAllUsers() {
        return service.getAllUtilisateurs();
    }
    @GetMapping("/getMailUser")
    public Utilisateur getUtilisateurByEmail(@RequestParam String email) {
        return service.getUtilisateurByEmail(email);
    }
    @GetMapping("/checkEmailExist")
    public boolean CheckEmailExist(@RequestParam String email) {
        return service.CheckEmailExist(email);
    }
    @GetMapping("/getAllAdmin")
    public List<Utilisateur> getAllAdmin() {
        return service.getAllUtilisateurs();
    }
    
    @GetMapping("/getAllStudents")
    public List<Etudiant> getAllStudent() {
        return service.allEtudiants();
    }
    @GetMapping("/getAllTeachers")
    public List<Intervenant> getAllTeacher() {
        return service.allIntervenants();
    }
    @GetMapping("/getAllCours")
    public List<Cours> getAllCours() {
        return service.allCours();
    }
    @GetMapping("/getQuestions")
    public List<Question> getAllQuestions(@RequestParam Cours cours) {
        return service.allQuestions(cours);
    }
}
