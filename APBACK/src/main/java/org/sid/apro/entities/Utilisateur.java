package org.sid.apro.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Utilisateur implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean statut;
    private String nom;
    private String prenom;
    @Column(unique = true, nullable = false)
    private String email;
    private String numTel;
    private String imageProfile;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
