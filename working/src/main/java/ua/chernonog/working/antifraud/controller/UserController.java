package ua.chernonog.working.antifraud.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.service.UserService;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {

    UserService userService;

    @PostMapping("/api/auth/user")
    UserRes regUser(@RequestBody UserReq user) {
        return userService.saveUser(user);
    }

}
