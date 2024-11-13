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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "etudiant")
    private Collection<Forme> formes;
}
