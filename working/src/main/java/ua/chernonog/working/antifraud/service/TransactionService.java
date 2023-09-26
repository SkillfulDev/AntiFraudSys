package ua.chernonog.working.antifraud.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import ua.chernonog.working.antifraud.model.emuns.Permission;
import ua.chernonog.working.antifraud.model.respons.TransactionResponse;

@Service
public class TransactionService {
    public TransactionResponse getResultOfPermission(long amount) {

        TransactionResponse response = new TransactionResponse();

        if (amount<0) {

            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        } else if (amount <= 1500&&amount>200) {
            response.setResult(Permission.MANUAL_PROCESSING);
        } else if (amount <= 200) {
            response.setResult(Permission.ALLOWED);
        }else {
            response.setResult(Permission.PROHIBITED);
        }
        return response;
    }

}
