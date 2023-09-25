package ua.chernonog.working.antifraud.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ua.chernonog.working.antifraud.entity.IpEntity;
import ua.chernonog.working.antifraud.model.request.IpRequest;
import ua.chernonog.working.antifraud.model.respons.IpResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-25T15:31:39+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)"
)
@Component
public class IpMapperImpl implements IpMapper {

    @Override
    public IpEntity ipReqToIpEntity(IpRequest ipRequest) {
        if ( ipRequest == null ) {
            return null;
        }

        IpEntity.IpEntityBuilder ipEntity = IpEntity.builder();

        ipEntity.ip( ipRequest.getIp() );

        return ipEntity.build();
    }

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

    @Override
    public IpResponse ipEntityToIpRequest(IpEntity ipEntity) {
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
