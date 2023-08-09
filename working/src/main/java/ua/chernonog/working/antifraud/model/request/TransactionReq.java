package ua.chernonog.working.antifraud.model.request;

import lombok.*;

@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
public class TransactionReq {
    long amount;
}
