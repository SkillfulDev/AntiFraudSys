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
    @JsonView(View.IpInternal.class)
    long id;

    @JsonView(View.IpInternal.class)
    String ip;

    @JsonView(View.IpTop.class)
    String status;
}
