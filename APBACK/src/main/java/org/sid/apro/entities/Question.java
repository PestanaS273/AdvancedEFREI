package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Question implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;
    @OneToMany(mappedBy="question")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Forme> formes;
}
