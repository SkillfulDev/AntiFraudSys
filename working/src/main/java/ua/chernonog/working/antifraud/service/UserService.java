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

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
//@NoArgsConstructor
@Transactional
public class UserService {
    UserRepository userRepository;

    public UserRes saveUser(UserReq user) {
        Optional<UserEntity> userEntity = userRepository.findByNameIgnoreCase(user.getName());
        log.info("userEntity is ={} ", userEntity.isPresent());
        UserRes userRes = null;
        if (userEntity.isPresent()) {
            UserEntity userFromDB = userEntity.get();
            userRes = new UserRes()
                    .setId(userFromDB.getId())
                    .setName(userFromDB.getName())
                    .setUsername(userFromDB.getUsername());
        } else {
            UserEntity savedUser = userRepository.save(UserEntity.builder()
                    .name(user.getName())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build());
            userRes = new UserRes()
                    .setId(savedUser.getId())
                    .setName(savedUser.getName())
                    .setUsername(savedUser.getUsername());
        }
        return userRes;
    }
}
