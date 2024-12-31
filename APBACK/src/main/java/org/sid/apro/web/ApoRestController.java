package org.sid.apro.web;

import jakarta.transaction.Transactional;
import org.sid.apro.entities.*;
import org.sid.apro.service.IAproIniService;
import org.sid.apro.vo.EtudiantReponseVO;
import org.sid.apro.vo.FormeVO;
import org.sid.apro.vo.NewFormeVO;
import org.sid.apro.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@CrossOrigin("*")
public class ApoRestController {

    @Autowired
    private IAproIniService service;
    @PostMapping("/inscrire")
    public Utilisateur saveUser(@RequestBody User user) {
        return service.saveUtilisateur(user.getEmail(), user.getPassword(), user.getRole()) ;
    }
    @PostMapping("/create")
    public Utilisateur createUser(@RequestBody User user) {
        return service.createEtudiant(user.getEmail(), user.getPassword(), user.getRole());
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
    @GetMapping("/getNombreEtudiantActive")
    public int NombreUserActive(){
        return service.NombreEtudiantsActif();
    }
    @GetMapping("/getAllForme")
    public List<Forme> getAllForme(@RequestParam int idCours, @RequestParam int idStudent) {
        return service.getAllForme(idCours, idStudent);
    }
    @GetMapping("/getAllCoursEtudiants")
    public List<Cours> getAllCoursEtudiants(@RequestParam int id){
        return service.getAllCoursEtudiants(id);
    }
    @PostMapping("/saveReponse")
    public ArrayList<Forme> saveReponse(@RequestBody FormeVO formeVO){
        return service.saveQuestion(formeVO);
    }
    @GetMapping("/getEtudiantByReponse")
    public Etudiant getEtudiantFromReponse(@RequestParam long idForme){
        return service.getEtudiantFromResponse(idForme);
    }
    @PostMapping("/addForme")
    public Forme addForme(@RequestBody NewFormeVO NewformeVO){
        return service.createForme(NewformeVO);
    }
    @GetMapping("/getAllEtudiantByCours")
    public List<Etudiant> getEtudiantFromCours(@RequestParam long idCours){
        return service.getEtudiantsFromCours(idCours);
    }
    @GetMapping("/getAllFoms")
    public List<Forme> getAllFoms(){
        return service.getAllFroms();
    }
    @GetMapping("/getAllFromsfromCours")
    public List<Forme> getAllFromsfromCours(@RequestParam long idCours){
        return service.getAllFromsFromCours(idCours);
    }
    /*@GetMapping("/initProf")
    public int initProf(){
        return service.initProf();
    }*/

}
