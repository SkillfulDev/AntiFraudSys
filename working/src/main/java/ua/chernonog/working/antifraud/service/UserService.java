package ua.chernonog.working.antifraud.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;
import ua.chernonog.working.antifraud.entity.UserEntity;
//import ua.chernonog.working.antifraud.mapper.UserEntityToUserDetails;
import ua.chernonog.working.antifraud.mapper.UserEntityToUserRes;
import ua.chernonog.working.antifraud.mapper.UserReqToUserEntity;
import ua.chernonog.working.antifraud.model.emuns.Operation;
import ua.chernonog.working.antifraud.model.emuns.Role;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.repository.UserRepository;

import java.util.List;
import java.util.Map;
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


    @Transactional
    public UserRes saveUser(UserReq user) {
        UserRes userRes;
        UserEntity userEntity;
        String hashPassword = passwordEncoder.encode(user.getPassword());
        if (userRepository.count() == 0) {
            userEntity = UserEntity.builder()
                    .name(user.getName())
                    .username(user.getUsername())
                    .password(hashPassword)
                    .role(Role.ADMINISTRATOR)
                    .status("User " + user.getName() + " unlocked")
                    .build();
        } else if (userRepository.existsByNameIgnoreCase(user.getName())) {
            throw new ErrorResponseException(HttpStatus.CONFLICT);
        } else {

            userEntity = UserEntity.builder()
                    .name(user.getName())
                    .username(user.getUsername())
                    .password(hashPassword)
                    .role(Role.MERCHANT)
                    .status("User " + user.getName() + " locked")
                    .build();
        }
        userRepository.save(userEntity);
        userRes = userEntityToUserRes.toUserRes(userEntity);
        return userRes;
    }

    @Transactional(readOnly = true)
    public List<UserRes> getUsers() {
        return userRepository.findAll()
                .stream().map(c -> userEntityToUserRes.toUserRes(c))
                .toList();
    }

    @Transactional
    public void delete(String username) {
        if (!userRepository.existsByUsernameIgnoreCase(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            userRepository.deleteByUsernameIgnoreCase(username);
        }
    }

    public UserRes changeRole(String username, Role role) {
        UserRes foundUser = getUserRes(username);
        if (foundUser.getRole() == Role.ADMINISTRATOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (role == foundUser.getRole()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else {
            foundUser.setRole(role);
            userRepository.updateRoleByUsername(role, username);
        }
        return foundUser;
    }

    private UserRes getUserRes(String username) {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .map(c -> userEntityToUserRes.toUserRes(c))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserRes changeStatus(String username, Operation operation) {
        UserRes foundUser = getUserRes(username);
        if (foundUser.getRole() == Role.ADMINISTRATOR) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (operation == Operation.UNLOCK) {
            foundUser.setStatus("User " + foundUser.getName() + " unlocked!");
        } else if (operation == Operation.LOCK) {
            foundUser.setStatus("User " + foundUser.getName() + " locked!");
        }
        userRepository.updateStatusByUsername(foundUser.getStatus(), foundUser.getUsername());

        return foundUser;

    }
}


