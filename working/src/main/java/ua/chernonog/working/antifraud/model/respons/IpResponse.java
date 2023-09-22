package ua.chernonog.working.antifraud.model.respons;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpResponse {
    long id;

    String ip;

    String status;
}
