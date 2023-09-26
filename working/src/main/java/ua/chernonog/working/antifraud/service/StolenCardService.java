package ua.chernonog.working.antifraud.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import ua.chernonog.working.antifraud.entity.StolenCardEntity;
import ua.chernonog.working.antifraud.mapper.StolenCardMapper;
import ua.chernonog.working.antifraud.model.respons.StolenCardResponse;
import ua.chernonog.working.antifraud.repository.StolenCardRepository;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class StolenCardService {
    StolenCardRepository stolenCardRepository;
    StolenCardMapper stolenCardMapper;


    public StolenCardResponse saveStolenCard(String cardNumber) {


        if (!checkSum(cardNumber)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (stolenCardRepository.existsByNumberIgnoreCase(cardNumber)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else {
            StolenCardEntity stolenCardEntity = StolenCardEntity.builder().number(cardNumber).build();
            var stc = stolenCardRepository.save(stolenCardEntity);
            log.info("card={}", stc);
            return stolenCardMapper.stolenEntityToStolenResponse(stc);
        }

    }

    private boolean checkSum(String cardNumber) {
        int sum = 0;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = cardNumber.charAt(i) - '0';
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return sum % 10 == 0;
    }

    public StolenCardResponse delete(String number) {
        StolenCardEntity stolenCardEntity =stolenCardRepository.findByNumber(number)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

         stolenCardRepository.delete(stolenCardEntity);
         StolenCardResponse stolenCardResponse = stolenCardMapper.stolenEntityToStolenResponse(stolenCardEntity);
         stolenCardResponse.setStatus("Card "+ number +" successfully removed!");

         return stolenCardResponse;
    }
}
