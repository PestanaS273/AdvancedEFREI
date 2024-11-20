package org.sid.apro;

import org.sid.apro.entities.Role;
import org.sid.apro.service.IAproIniService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class AproApplication {

    public static void main(String[] args) {

        SpringApplication.run(AproApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    CommandLineRunner start(IAproIniService aproIniService) {
        return args -> {
            aproIniService.saveRole(new Role(1, "etudiant"));
            aproIniService.saveRole(new Role(2, "professor"));
            aproIniService.saveRole(new Role(3, "admin"));
            Stream.of("user4@gmail.com", "user2@gmail.com", "user3@gmail.com", "admin@gmail.com").forEach(un -> {
                aproIniService.saveUtilisateur(un, "1234", "1234");
            });
            aproIniService.addRoleToUser("admin@gmail.com", "admin");
        };
    }*/

}
