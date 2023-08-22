package ua.chernonog.working.antifraud.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;
import ua.chernonog.working.antifraud.entity.UserEntity;
//import ua.chernonog.working.antifraud.mapper.UserEntityToUserDetails;
import ua.chernonog.working.antifraud.mapper.UserEntityToUserRes;
import ua.chernonog.working.antifraud.mapper.UserReqToUserEntity;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor

@Service
public class UserService {
    UserRepository userRepository;

    UserEntityToUserRes userEntityToUserRes;

    UserReqToUserEntity userEntity;

    PasswordEncoder passwordEncoder;


//    @Transactional
    public UserRes saveUser(UserReq user) {
        UserRes userRes;
        if (userRepository.existsByNameIgnoreCase(user.getName())) {
            throw new ErrorResponseException(HttpStatus.CONFLICT);
        } else {
            String hashPassword= passwordEncoder.encode(user.getPassword());
            var savedUser = UserEntity.builder()
                    .name(user.getName())
                    .username(user.getUsername())
                    .password(hashPassword).build();
            savedUser = userRepository.save(savedUser);
            userRes = userEntityToUserRes.toUserRes(savedUser);
        }
        return userRes;
    }

    @Transactional(readOnly = true)
    public List<UserRes> getUsers() {
        return userRepository.findAll()
                .stream().map(c -> userEntityToUserRes.toUserRes(c))
                .collect(Collectors.toList());

    }
}
