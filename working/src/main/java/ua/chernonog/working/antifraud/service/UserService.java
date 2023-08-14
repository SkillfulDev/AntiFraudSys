package ua.chernonog.working.antifraud.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.mapper.UserResToEntity;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.repository.UserRepository;

@Slf4j
@AllArgsConstructor
@Service
//@NoArgsConstructor
//@Transactional
public class UserService {
    UserRepository userRepository;

    public void saveUser(UserReq user) {
        log.info("name={} username={} password={}",
                user.getName(), user.getUsername(), user.getPassword());
//        UserEntity userEntity = UserEntity.builder()
//                .name(user.getName())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .build();
//        log.info("UserEntity name ={}",userEntity.getName());
        userRepository.save(UserEntity.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build());
    }
}
