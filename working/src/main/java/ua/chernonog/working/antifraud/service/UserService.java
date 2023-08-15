package ua.chernonog.working.antifraud.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.mapper.UserEntityToUserRes;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
//@NoArgsConstructor
@Transactional
public class UserService {
    UserRepository userRepository;
    UserEntityToUserRes userEntityToUserRes;

    public UserRes saveUser(UserReq user) {
        Optional<UserEntity> userEntity = userRepository.findByNameIgnoreCase(user.getName());
        log.info("userEntity is ={} ", userEntity.isPresent());
        UserRes userRes = null;
        if (userEntity.isPresent()) {
             throw new ErrorResponseException(HttpStatus.CONFLICT);
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

    public List<UserRes> getUsers() {
      return  userRepository.findAll().stream().map(userEntityToUserRes::toUserRes)
              .collect(Collectors.toList());

    }
}
