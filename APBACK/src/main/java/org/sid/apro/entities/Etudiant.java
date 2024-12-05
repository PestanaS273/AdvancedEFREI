package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@PrimaryKeyJoinColumn(name = "id")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Etudiant extends Utilisateur{
    private Boolean etat;
    private Long NumEtudiant;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Cours> cours = new ArrayList<>();
    @OneToMany(mappedBy = "etudiant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<EtudiantCoursForm> etudiantCoursForms = new ArrayList<>();

}
