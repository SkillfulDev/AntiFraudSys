package ua.chernonog.working.antifraud.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.service.UserService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {

    UserService userService;

    @PostMapping("/api/auth/user")
    @ResponseStatus(HttpStatus.CREATED)
   public UserRes regUser(@Valid @RequestBody UserReq user) {
        log.info("request = {}", user);
        return userService.saveUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(new UserRes(2222,"SAM","BEAN"));
    }

    @GetMapping("/api/auth/list")
    public List<UserRes> getUsers() {
        return userService.getUsers();

    }

}
