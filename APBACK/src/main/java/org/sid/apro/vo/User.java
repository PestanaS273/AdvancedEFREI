package org.sid.apro.vo;

import jakarta.transaction.Transactional;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Transactional @Getter @Setter
public class User {

    private String email;
    private String password;


}
