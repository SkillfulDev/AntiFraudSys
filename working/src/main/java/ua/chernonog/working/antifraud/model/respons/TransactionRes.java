package ua.chernonog.working.antifraud.model.respons;

import lombok.*;
import ua.chernonog.working.antifraud.model.Permission;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRes {
    Enum<Permission> result;
}
