package ua.chernonog.working.antifraud.model.respons;

import lombok.*;
import ua.chernonog.working.antifraud.model.emuns.Permission;
import ua.chernonog.working.antifraud.model.emuns.Reason;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    Enum<Permission> result;

    String info;


}
