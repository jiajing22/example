package com.example.demo.controller;

import com.example.demo.entity.Donor;
import com.example.demo.entity.Staff;
import com.example.demo.entity.User;
import com.example.demo.service.DonorService;
import com.example.demo.service.StaffService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/eDonor")
public class UserController {

    @Autowired
    private UserService userService;
//
//    @PostMapping("/users")
//    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
//        return userService.createUser(user);
//    }
//    @GetMapping("/users/{userId}")
//    public User getUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
//        return userService.getUser(userId);
//    }
//    @PutMapping("/users")
//    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
//        return userService.updateUser(user);
//    }
//    @DeleteMapping("/users/{userId}")
//    public String deleteUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
//        return userService.deleteUser(userId);
//    }
//
    @GetMapping("/users")
    public List<User> getAllUser() throws ExecutionException, InterruptedException {
        return userService.getAllUser();
    }

    @Autowired
    private StaffService staffService;

    @PostMapping("/staff")
    public String addStaff(@RequestBody Staff staff) throws ExecutionException, InterruptedException {
        return staffService.addStaff(staff);
    }
    @GetMapping("/staff/{documentId}")
    public Staff getStaff(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return staffService.getStaff(documentId);
    }
    @PutMapping("/staff")
    public String updateStaff(@RequestBody Staff staff) throws ExecutionException, InterruptedException {
        return staffService.updateStaff(staff);
    }
    @DeleteMapping("/staff/{documentId}")
    public String deleteStaff(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return staffService.deleteStaff(documentId);
    }

    @GetMapping("/staff")
    public List<Staff> getAllStaff() throws ExecutionException, InterruptedException {
        return staffService.getAllStaff();
    }

    @Autowired
    private DonorService donorService;

    @PostMapping("/donor")
    public String addDonor(@RequestBody Donor donor) throws ExecutionException, InterruptedException {
        System.out.println("Controller");
        System.out.println(donor);
        return donorService.addDonor(donor);
    }
    @GetMapping("/donor/{documentId}")
    public Donor getDonor(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return donorService.getDonor(documentId);
    }
    @PutMapping("/donor")
    public String updateDonor(@RequestBody Donor donor) throws ExecutionException, InterruptedException {
        return donorService.updateDonor(donor);
    }
    @DeleteMapping("/donor/{documentId}")
    public String deleteDonor(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return donorService.deleteDonor(documentId);
    }

    @GetMapping("/donor")
    public List<Donor> getAllDonor() throws ExecutionException, InterruptedException {
        return donorService.getAllDonor();
    }

    @GetMapping("/users/{userId}")
    public String getUserIdByUsername(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return userService.getUserIdByUsername(userId);
    }

    @RequestMapping(value = "/user-login/", method = RequestMethod.POST)
    public String getUserId(@RequestBody User user)throws Exception {
        return userService.validateUserLogin(user.getUserId(), user.getPassword());
    }
}
