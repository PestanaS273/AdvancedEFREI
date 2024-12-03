package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cours implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String nomCours;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Forme forme;



}
