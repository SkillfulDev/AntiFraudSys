package ua.chernonog.working.antifraud.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserDelRes;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.service.UserService;

import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {

    UserService userService;

    @PostMapping("/api/auth/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRes regUser(@Valid @RequestBody UserReq user) {
        log.info("request = {}", user.getLocalTime());
        return userService.saveUser(user);
    }

    @GetMapping("/api/auth/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserRes> getUsers() {
        return userService.getUsers();
    }

//    @PutMapping("/api/auth/role")
//    @ResponseStatus(HttpStatus.OK)
//    public UserRes changeRole(@RequestBody Map<String, Object> requestMap) {
//
//        log.info("Map includes ={}",requestMap.values());
//        return userService.changeRole(requestMap);
////        return null;
//    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/api/auth/user/{username}")
    public UserDelRes deleteUser(@PathVariable(value = "username") String username) {
        log.info("{}->username", username);
        userService.delete(username);
        return new UserDelRes(username,"Deleted successfully!");
    }



}
