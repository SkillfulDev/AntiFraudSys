package ua.chernonog.working.antifraud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.model.request.UserReq;

@Mapper( componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserReqToUserEntity {
    UserEntity userReqToUserEntity(UserReq userReq);
}
