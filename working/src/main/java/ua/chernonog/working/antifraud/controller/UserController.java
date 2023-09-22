package ua.chernonog.working.antifraud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.working.antifraud.config.View;
import ua.chernonog.working.antifraud.model.request.UserReq;
import ua.chernonog.working.antifraud.model.respons.UserDelRes;
import ua.chernonog.working.antifraud.model.respons.UserRes;
import ua.chernonog.working.antifraud.service.UserService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {

    UserService userService;
    @JsonView(View.Summary.class)

    @PostMapping("/api/auth/user")
    @ResponseStatus(HttpStatus.CREATED)

    public UserRes regUser(@Valid @RequestBody UserReq user) {

        return userService.saveUser(user);
    }

    @GetMapping("/api/auth/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserRes> getUsers() {
        return userService.getUsers();
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/api/auth/user/{username}")
    public UserDelRes deleteUser(@PathVariable(value = "username") String username) {
        log.info("{}->username", username);
        userService.delete(username);
        return new UserDelRes(username, "Deleted successfully!");
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/api/auth/role")
    @ResponseStatus(HttpStatus.OK)
    public UserRes changeRole(@RequestBody UserReq userReq) {
        return userService.changeRole(userReq.getUsername(), userReq.getRole());
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/api/auth/access")
    @ResponseStatus(HttpStatus.OK)
    public UserRes changeStatus(@RequestBody UserReq userReq) {
        return userService.changeStatus(userReq.getUsername(), userReq.getOperation());
    }

}
