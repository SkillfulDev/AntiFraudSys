package ua.chernonog.working.antifraud.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ua.chernonog.working.antifraud.entity.IpEntity;
import ua.chernonog.working.antifraud.mapper.IpMapper;
import ua.chernonog.working.antifraud.model.request.IpRequest;
import ua.chernonog.working.antifraud.model.respons.IpResponse;
import ua.chernonog.working.antifraud.repository.IpRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class IpService {
    IpRepository ipRepository;
    IpMapper ipMapper;

    @Transactional
    public IpResponse saveSuspiciousIp(String ip) {
        if (!ipRepository.existsByIp(ip)){
            return ipMapper.ipEntityToIpResponse(ipRepository.save(IpEntity.builder().ip(ip).build()));
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }


    public void deleteSucpiciousIp(String ip) {
       Optional<IpRequest> foundedIp=ipRepository.findByIp(ip);
        ipRepository.delete(ipMapper.ipReqToIpEntity(c->IpMapper::ipReqToIpEntity(c));
    }
}
