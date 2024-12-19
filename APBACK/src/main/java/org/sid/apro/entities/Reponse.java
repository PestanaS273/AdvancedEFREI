package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Reponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reponse;
    @OneToMany(mappedBy="reponse")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Forme> formes;
}
