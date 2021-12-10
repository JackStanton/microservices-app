package com.application.fluxserver.controller;

import com.application.fluxserver.entity.UserEntity;
import com.application.fluxserver.servise.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Flux<UserEntity> getUsers(){
        return userService.getAllUsers();
    }

}
