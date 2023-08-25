package ua.chernonog.working.antifraud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ua.chernonog.working.antifraud.entity.UserEntity;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring"
        , unmappedSourcePolicy = IGNORE
        , unmappedTargetPolicy = IGNORE)
public interface UserEntityToUserDetails {

//    User userEntityToUserDetails(UserEntity userEntity);
}
