package ua.chernonog.working.antifraud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.chernonog.working.antifraud.annotations.IsSupport;
import ua.chernonog.working.antifraud.config.View;
import ua.chernonog.working.antifraud.model.request.StolenCardRequest;
import ua.chernonog.working.antifraud.model.respons.StolenCardResponse;
import ua.chernonog.working.antifraud.service.StolenCardService;

@AllArgsConstructor
@RestController
@Slf4j
public class StolenCardController {
    StolenCardService stolenCardService;

    @IsSupport
    @JsonView(View.CardInternal.class)
    @PostMapping("/api/antifraud/stolencard")
    public StolenCardResponse addStolenCard(@RequestBody @Valid StolenCardRequest stolenCardRequest) {
        return stolenCardService.saveStolenCard(stolenCardRequest.getNumber());
    }

    @IsSupport
    @JsonView(View.CardTop.class)
    @DeleteMapping("/api/antifraud/stolencard/{number}")
    public StolenCardResponse deleteStolenCard(@PathVariable ("number")String number){
        log.info("number={}",number);
        if (!number.matches("^\\d{13,16}$")){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return stolenCardService.delete(number);
    }
}
