package ua.chernonog.working.antifraud.model.respons;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {

    int id;

    String name;

    String username;
}
