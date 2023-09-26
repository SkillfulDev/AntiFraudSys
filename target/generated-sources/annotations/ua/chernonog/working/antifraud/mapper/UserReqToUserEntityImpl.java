package ua.chernonog.working.antifraud.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.model.request.UserReq;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-26T11:21:42+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)"
)
@Component
public class UserReqToUserEntityImpl implements UserReqToUserEntity {

    @Override
    public UserEntity userReqToUserEntity(UserReq userReq) {
        if ( userReq == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.name( userReq.getName() );
        userEntity.username( userReq.getUsername() );
        userEntity.password( userReq.getPassword() );
        userEntity.role( userReq.getRole() );

        return userEntity.build();
    }
}
