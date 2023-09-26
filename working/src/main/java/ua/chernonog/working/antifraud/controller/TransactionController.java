package ua.chernonog.working.antifraud.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.working.antifraud.annotations.IsMerchant;
import ua.chernonog.working.antifraud.model.request.TransactionRequest;
import ua.chernonog.working.antifraud.model.respons.TransactionResponse;
import ua.chernonog.working.antifraud.service.TransactionService;

@AllArgsConstructor
@RestController
@Slf4j
public class TransactionController {
    TransactionService transactionService;

    @IsMerchant
    @PostMapping("/api/antifraud/transaction")
    @ResponseStatus(HttpStatus.OK)
    TransactionResponse statusOfPermission(@RequestBody TransactionRequest req) {
        return transactionService.getResultOfPermission(req.getAmount(),req.getIp(),req.getNumber());
    }
}
