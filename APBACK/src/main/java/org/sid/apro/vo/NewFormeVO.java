package org.sid.apro.vo;

import lombok.*;

import java.util.List;
@Data @NoArgsConstructor
@AllArgsConstructor @ToString @Setter @EqualsAndHashCode
public class NewFormeVO {
    List<String> questions;
    List<String> response;
}
