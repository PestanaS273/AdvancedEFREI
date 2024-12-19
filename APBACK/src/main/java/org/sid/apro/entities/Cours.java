package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cours implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String nomCours;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<Forme> formes = new ArrayList<>();
    @OneToMany(mappedBy = "cours", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<EtudiantCoursForm> etudiantCoursForms = new ArrayList<>();
    @ManyToMany(mappedBy = "cours", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Etudiant> etudiants = new ArrayList<>();




}
