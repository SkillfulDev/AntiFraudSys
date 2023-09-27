package ua.chernonog.working.antifraud.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import ua.chernonog.working.antifraud.model.emuns.Permission;
import ua.chernonog.working.antifraud.model.emuns.Reason;
import ua.chernonog.working.antifraud.model.respons.TransactionResponse;
import ua.chernonog.working.antifraud.repository.IpRepository;
import ua.chernonog.working.antifraud.repository.StolenCardRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionService {
    IpRepository ipRepository;
    StolenCardRepository stolenCardRepository;

    public TransactionResponse getResultOfPermission(long amount, String ip, String number) {

        TransactionResponse response = new TransactionResponse();
        boolean isValidNumber = stolenCardRepository.existsByNumberIgnoreCase(number);
        boolean isValidIp = ipRepository.existsByIp(ip);


        if (amount < 0) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        } else if (amount <= 1500 && amount > 200
                && !isValidIp && !isValidNumber) {
            response.setResult(Permission.MANUAL_PROCESSING);
            getReason(amount, isValidNumber, isValidIp);

        } else if (amount <= 200
                && !isValidIp && !isValidNumber) {
            response.setResult(Permission.ALLOWED);
            response.setInfo("none");

        } else {
            response.setResult(Permission.PROHIBITED);

        }
        return response;
    }

    private List<String> getReason(long amount, boolean isValidNumber, boolean isValidIp) {
        List<String> rejection = new ArrayList<>();
        rejection.add(List.of("card_number", "ip").toString());


        if (!isValidIp) {
            rejection.remove("ip");
        }
        if (!isValidNumber) {
            rejection.remove("card_number");
        }
        if (amount <= 1500 && amount > 200) {
            rejection.add("amount");
        }
        return rejection;
    }
}


