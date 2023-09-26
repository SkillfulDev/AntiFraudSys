package ua.chernonog.working.antifraud.model.respons;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class StolenCardResponse {
    long id;

    String number;

    String status;
}
