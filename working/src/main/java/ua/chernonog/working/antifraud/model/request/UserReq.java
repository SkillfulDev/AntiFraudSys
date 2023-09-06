package ua.chernonog.working.antifraud.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

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

    @NotNull
    LocalDate localDate;
}
