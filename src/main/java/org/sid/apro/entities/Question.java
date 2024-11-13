package org.sid.apro.entities;

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
    private Collection<Forme> formes;
}
