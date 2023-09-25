package ua.chernonog.working.antifraud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.chernonog.working.antifraud.entity.IpEntity;
import ua.chernonog.working.antifraud.model.request.IpRequest;
import ua.chernonog.working.antifraud.model.respons.IpResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IpMapper {
    IpEntity ipReqToIpEntity(IpRequest ipRequest);
    IpResponse ipEntityToIpResponse(IpEntity ipEntity);

    IpResponse ipEntityToIpRequest(IpEntity ipEntity);
}
