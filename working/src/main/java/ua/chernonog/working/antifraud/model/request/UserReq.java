package ua.chernonog.working.antifraud.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

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

    LocalTime localTime = LocalTime.now();
}
