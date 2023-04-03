package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.createUser(user);
    }
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return userService.getUser(userId);
    }
    @PutMapping("/users")
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user);
    }
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return userService.deleteUser(userId);
    }

    @GetMapping("/users")
    public List<User> getAllUser() throws ExecutionException, InterruptedException {
        return userService.getAllUser();
    }
}
