package ua.chernonog.working.antifraud.mapper;

import org.mapstruct.Mapper;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.model.respons.UserRes;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = IGNORE
)
public interface UserMapper {
    UserRes toUserRes(UserEntity userEntity);
}
