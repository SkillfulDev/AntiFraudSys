package ua.chernonog.working.antifraud.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.model.respons.UserRes;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-26T13:39:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserRes toUserRes(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserRes userRes = new UserRes();

        userRes.setId( userEntity.getId() );
        userRes.setName( userEntity.getName() );
        userRes.setUsername( userEntity.getUsername() );
        userRes.setRole( userEntity.getRole() );
        userRes.setStatus( userEntity.getStatus() );

        return userRes;
    }
}
