package ua.chernonog.working.antifraud.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import ua.chernonog.working.antifraud.model.emuns.Operation;
import ua.chernonog.working.antifraud.model.emuns.Role;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Valid
public class UserReq {
    @NotNull
    String name;
    @NotNull
    String username;
    @NotNull
    String password;

    Role role;

    Operation operation;


}
