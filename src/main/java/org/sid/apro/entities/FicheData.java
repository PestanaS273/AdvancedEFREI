package org.sid.apro.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class FicheData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] fiche;
    public byte[] getFiche(){
        return fiche;
    }
}
