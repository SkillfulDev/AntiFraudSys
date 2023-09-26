package ua.chernonog.working.antifraud.model.respons;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ua.chernonog.working.antifraud.config.View;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpResponse {
    @JsonView(View.Public.class)
    long id;

    @JsonView(View.Public.class)
    String ip;

    @JsonView(View.Summary.class)
    String status;
}
