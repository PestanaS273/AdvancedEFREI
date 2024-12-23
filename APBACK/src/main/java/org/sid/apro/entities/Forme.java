package org.sid.apro.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Forme implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean anonyme;
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
    private Question question;
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
    private Reponse reponse;
    @OneToMany(mappedBy = "forme", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<EtudiantCoursForm> etudiantCoursForms = new ArrayList<>();
    @ManyToOne
    private Etudiant etudiant;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Collection<QuestionReponse> questionReponses = new ArrayList<>();


}
