package ua.chernonog.working.antifraud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.model.respons.UserRes;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring"
        ,unmappedTargetPolicy = IGNORE
        ,unmappedSourcePolicy = IGNORE)

public interface UserEntityToUserRes {

    UserRes toUserRes(UserEntity userEntity);
}
