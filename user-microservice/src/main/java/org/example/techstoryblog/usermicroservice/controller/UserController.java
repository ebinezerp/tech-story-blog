package org.example.techstoryblog.usermicroservice.controller;

import org.example.techstoryblog.usermicroservice.model.User;
import org.example.techstoryblog.usermicroservice.servcie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> get(){
        return new ResponseEntity<List<User>>(userService.get(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Long id){
        User user = userService.get(id);
        return Optional.of(user).
                map( u -> new ResponseEntity(u,HttpStatus.OK))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User "+ id + "is not found")
                );
    }

}
