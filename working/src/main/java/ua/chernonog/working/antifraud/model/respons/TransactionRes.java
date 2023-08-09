package ua.chernonog.working.antifraud.model.respons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.chernonog.working.antifraud.model.enumreq.Permission;

@Getter@Setter@Builder@ToString
public class TransactionRes {
    Enum<Permission> result;
}
