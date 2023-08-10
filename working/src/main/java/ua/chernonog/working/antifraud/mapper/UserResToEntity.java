package ua.chernonog.working.antifraud.mapper;

import org.mapstruct.Mapper;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;

@Mapper
public interface UserResToEntity {

    UserEntity toUserEntity(UserReq userReq);
}
