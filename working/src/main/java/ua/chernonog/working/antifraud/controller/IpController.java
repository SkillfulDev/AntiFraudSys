package ua.chernonog.working.antifraud.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.working.antifraud.annotations.IsAministrator;
import ua.chernonog.working.antifraud.annotations.IsSupport;
import ua.chernonog.working.antifraud.model.request.IpRequest;
import ua.chernonog.working.antifraud.model.respons.IpResponse;
import ua.chernonog.working.antifraud.service.IpService;
@Slf4j
@RestController
@AllArgsConstructor
public class IpController {

    IpService ipService;

    @PostMapping("/api/antifraud/suspicious-ip")
    @IsAministrator
    public IpResponse saveSuspiciousIp(@RequestBody @Valid IpRequest ipRequest) {
        log.info("ipReq={}",ipRequest.getIp());
        return ipService.saveSuspiciousIp(ipRequest.getIp());
    }
    @IsSupport
    @DeleteMapping("/api/antifraud/suspicious-ip/{ip}")
    void deleteSuspiciousIp(@PathVariable("ip") String ip , @Valid @RequestBody IpRequest ipRequest){
        ipService.deleteSucpiciousIp(ipRequest.getIp());
    }
}
