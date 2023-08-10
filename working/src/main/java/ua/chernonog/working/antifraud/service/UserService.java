package ua.chernonog.working.antifraud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.mapper.UserResToEntity;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    public void saveUser(UserReq user) {
     UserEntity userEntity= new UserEntity();
     userEntity.builder().name(user.getName()).username(user.getUsername()).password(user.getPassword()).build();

        userRepository.save(userEntity);
    }
}
