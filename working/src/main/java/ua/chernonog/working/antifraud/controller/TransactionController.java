package ua.chernonog.working.antifraud.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.chernonog.working.antifraud.model.request.TransactionReq;
import ua.chernonog.working.antifraud.model.respons.TransactionRes;
import ua.chernonog.working.antifraud.service.TransactionService;

@AllArgsConstructor
@RestController
@Slf4j
public class TransactionController {
    TransactionService transactionService;

    @PostMapping("/api/antifraud/transaction")
    TransactionRes statusOfPermission(@RequestBody TransactionReq req) {
        var result = TransactionRes.builder().result(transactionService.
                getResultOfPermission(req.getAmount())).build();
        log.warn("result is = {}",result);
        return result;
    }

}
