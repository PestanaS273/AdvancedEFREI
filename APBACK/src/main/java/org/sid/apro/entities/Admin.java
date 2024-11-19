package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends Utilisateur{
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Cours> cours = new ArrayList<>();
}
