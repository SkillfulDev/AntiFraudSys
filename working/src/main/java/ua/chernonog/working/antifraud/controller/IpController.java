package ua.chernonog.working.antifraud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.working.antifraud.annotations.IsAministrator;
import ua.chernonog.working.antifraud.annotations.IsSupport;
import ua.chernonog.working.antifraud.config.View;
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
    @JsonView(View.Summary.class)
    @DeleteMapping("/api/antifraud/suspicious-ip/{ip}")
    IpResponse deleteSuspiciousIp(@PathVariable("ip") String ip ){
        log.info("ip={}",ip);
        return ipService.deleteSucpiciousIp(ip);
    }
}
