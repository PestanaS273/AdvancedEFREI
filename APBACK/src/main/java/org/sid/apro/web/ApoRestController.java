package org.sid.apro.web;

import jakarta.transaction.Transactional;
import org.sid.apro.entities.Utilisateur;
import org.sid.apro.service.IAproIniService;
import org.sid.apro.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@CrossOrigin("*")
public class ApoRestController {

    @Autowired
    private IAproIniService service;
    @PostMapping("/inscrire")
    public Utilisateur saveUser(@RequestBody User user) {
        return service.saveUtilisateur(user.getEmail(), user.getPassword(), user.getConfirmPassword()) ;
    }
}
