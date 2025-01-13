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
@Data @AllArgsConstructor @NoArgsConstructor
public class Etudiant extends Utilisateur{
    private Boolean etat;
    private Long NumEtudiant;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(
            name = "etudiant_cours", // Table de jointure
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "cours_id")
    )

    private Collection<Cours> cours = new ArrayList<>();

}
