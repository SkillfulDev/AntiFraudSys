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
import ua.chernonog.working.antifraud.model.request.IpRequest;
import ua.chernonog.working.antifraud.model.respons.IpResponse;
import ua.chernonog.working.antifraud.service.IpService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class IpController {

    IpService ipService;

    @PostMapping("/api/antifraud/suspicious-ip")
    @IsSupport
    @JsonView(View.IpInternal.class)
    public IpResponse saveSuspiciousIp(@RequestBody @Valid IpRequest ipRequest) {
        log.info("ipReq={}", ipRequest.getIp());
        return ipService.saveSuspiciousIp(ipRequest.getIp());
    }

    @IsSupport
    @JsonView(View.IpTop.class)
    @DeleteMapping("/api/antifraud/suspicious-ip/{ip}")
    IpResponse deleteSuspiciousIp(@PathVariable(value = "ip") String ip) {
        log.info("ip={}", ip);
        if (!ip.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\." +
                "){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ipService.deleteSucpiciousIp(ip);
    }

    @IsSupport
    @GetMapping("/api/antifraud/suspicious-ip")
    public List<IpResponse> getListSuspiciousIp() {
        return ipService.getListSuspiciousIp();
    }
}
