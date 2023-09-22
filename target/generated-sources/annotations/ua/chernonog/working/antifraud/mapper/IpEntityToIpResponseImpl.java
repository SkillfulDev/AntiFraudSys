package ua.chernonog.working.antifraud.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ua.chernonog.working.antifraud.entity.IpEntity;
import ua.chernonog.working.antifraud.model.respons.IpResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-22T13:34:05+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)"
)
@Component
public class IpEntityToIpResponseImpl implements IpEntityToIpResponse {

    @Override
    public IpResponse ipEntityToIpResponse(IpEntity ipEntity) {
        if ( ipEntity == null ) {
            return null;
        }

        IpResponse ipResponse = new IpResponse();

        if ( ipEntity.getId() != null ) {
            ipResponse.setId( ipEntity.getId() );
        }
        ipResponse.setIp( ipEntity.getIp() );

        return ipResponse;
    }
}
