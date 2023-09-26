package ua.chernonog.working.antifraud.model.respons;

import lombok.*;
import ua.chernonog.working.antifraud.model.emuns.Permission;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    Enum<Permission> result;
}
