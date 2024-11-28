package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends Utilisateur{
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Cours> cours = new ArrayList<>();
}
