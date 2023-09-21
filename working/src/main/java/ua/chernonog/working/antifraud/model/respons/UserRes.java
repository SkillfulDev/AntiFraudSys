package ua.chernonog.working.antifraud.model.respons;

import lombok.*;
import lombok.experimental.Accessors;
import ua.chernonog.working.antifraud.model.emuns.Role;

import java.time.LocalDate;
import java.time.LocalTime;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {

    Long id;

    String name;

    String username;

    Role role;

    String status;



}
