package com.application.restserver.controller;

import com.application.restserver.entity.UserEntity;
import com.application.restserver.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:8081")
public class RestController {

    UserService userService;

    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Optional<UserEntity>> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping({"getByLogin/{login}"})
    public ResponseEntity<Optional<UserEntity>> getUser(@PathVariable String login) {
        return new ResponseEntity<>(userService.getByLogin(login), HttpStatus.OK);
    }


    @PostMapping({"/save"})
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity) {
        UserEntity user = userService.saveOrUpdate(userEntity);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<UserEntity> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
