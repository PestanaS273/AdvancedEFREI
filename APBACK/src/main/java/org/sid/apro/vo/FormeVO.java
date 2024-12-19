package org.sid.apro.vo;

import jakarta.transaction.Transactional;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Getter
@Setter

public class FormeVO {
    private long idEtudiant;
    private long idCours;
    private String question;
    private String reponse;
}
