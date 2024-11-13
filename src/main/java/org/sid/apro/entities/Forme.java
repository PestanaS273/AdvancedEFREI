package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Forme implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Etudiant etudiant;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Admin admin;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Intervenant intervenant;


}
