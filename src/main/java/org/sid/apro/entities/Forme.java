package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Forme implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(cascade = CascadeType.MERGE ,mappedBy = "forme")
    private Collection<Cours> cours;
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
    private Question question;
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
    private Reponse reponse;

}
