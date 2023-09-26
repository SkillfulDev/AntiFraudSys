package ua.chernonog.working.antifraud.model.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class StolenCardRequest {
    @Pattern(regexp = "^\\d{13,16}$")
    String number;

}
