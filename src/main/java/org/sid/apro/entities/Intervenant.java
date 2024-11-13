package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@PrimaryKeyJoinColumn(name = "id")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Intervenant extends Utilisateur{
    private String Specialiste;
    private String description;
    private int nmbEtudiant;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "intervenant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Forme> formes;
}
