package ua.chernonog.working.antifraud.model.respons;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import ua.chernonog.working.antifraud.config.View;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class StolenCardResponse {
    @JsonView(View.CardInternal.class)
    long id;
    @JsonView(View.CardInternal.class)
    String number;

    @JsonView(View.CardTop.class)
    String status;
}
