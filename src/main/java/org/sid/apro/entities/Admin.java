package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends Utilisateur{
    @OneToMany(cascade=CascadeType.MERGE ,mappedBy = "admin")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Collection<Forme> formes;
}
