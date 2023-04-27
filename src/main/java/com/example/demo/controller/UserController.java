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
    public ResponseEntity<Staff> getStaff(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        Staff staff = staffService.getStaff(documentId);
        if(staff == null){
            return new ResponseEntity<Staff>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Staff>(staff, HttpStatus.OK);
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

    @RequestMapping(value = "/staff/login", method = RequestMethod.POST)
    public ResponseEntity<?> getStaffId(@RequestBody User user)throws Exception {
        Staff staffId = staffService.validateStaffLogin(user.getUserId(), user.getPassword());
        if (staffId == null){
            return new ResponseEntity<>("Invalid username or password",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffId, HttpStatus.OK);
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
    public ResponseEntity<Donor> getDonor(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        Donor donor = donorService.getDonor(documentId, "D");
        if(donor == null){
            return new ResponseEntity<Donor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Donor>(donor, HttpStatus.OK);
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

    @RequestMapping(value = "/donor/login", method = RequestMethod.POST)
    public ResponseEntity<?> getDonorId(@RequestBody User user)throws Exception {
        Donor donorId = donorService.validateDonorLogin(user.getUserId(), user.getPassword());
        if(donorId == null){
            return new ResponseEntity<>("Invalid username or password",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(donorId, HttpStatus.OK);
    }
}
