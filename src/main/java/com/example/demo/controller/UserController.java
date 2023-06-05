package com.example.demo.controller;

import com.example.demo.entity.Donor;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.entity.Staff;
import com.example.demo.entity.User;
import com.example.demo.service.DonorService;
import com.example.demo.service.StaffService;
import com.example.demo.service.UserService;
import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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

//    @GetMapping("/users/{userId}")
//    public String getUserIdByUsername(@PathVariable String userId) throws ExecutionException, InterruptedException {
//        return userService.getUserIdByUsername(userId);
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
            return new ResponseEntity<Staff>(HttpStatus.OK);
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
            return new ResponseEntity<>("Invalid username or password",HttpStatus.OK);
        }
        return new ResponseEntity<>(staffId, HttpStatus.OK);
    }

//    @GetMapping("/staff/search-donor")
//    public ResponseEntity<List<Donor>> searchDonorById(@RequestParam(value="id") String id) throws Exception {
//        List<Donor> donors = donorService.findDonorById(id);
//        if (donors == null || donors.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(donors, HttpStatus.OK);
//    }

    @GetMapping("/staff/search-donor")
    public ResponseEntity<?> searchDonorById (@RequestParam(value="id") String id) throws Exception{
        Donor donor = donorService.findDonorById(id);
        if ( donor == null ){
            return new ResponseEntity<>("Donor not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(donor, HttpStatus.OK);
    }

//    --------------------------------------Donor----------------------------------------------------------------

    @Autowired
    private DonorService donorService;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/donor")
    public ResponseEntity<String> addDonor(@RequestBody Donor donor) throws Exception {
        String status = donorService.addDonor(donor);
        switch (status) {
            case "documentId":
                return new ResponseEntity<>("Existing document ID", HttpStatus.OK);
            case "userId":
                return new ResponseEntity<>("registeredIc", HttpStatus.OK);
            case "email":
                return new ResponseEntity<>("emailUsed", HttpStatus.OK);
            case "success":
                return new ResponseEntity<>("Registration Success.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/donor/{documentId}")
    public ResponseEntity<Donor> getDonor(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        Donor donor = donorService.getDonor(documentId);
        if(donor == null){
            return new ResponseEntity<Donor>(HttpStatus.OK);
        }
        return new ResponseEntity<Donor>(donor, HttpStatus.OK);
    }

    @PutMapping("/donor")
    public ResponseEntity<String> updateDonor(@RequestBody Donor donor) throws ExecutionException, InterruptedException {
        String updateDonor = donorService.updateDonor(donor);
        if(updateDonor == null){
            return new ResponseEntity<String>("Donor not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/donor/{documentId}")
    public String deleteDonor(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return donorService.deleteDonor(documentId);
    }

    @GetMapping("/donor")
    public List<Donor> getAllDonor() throws ExecutionException, InterruptedException {
        return donorService.getAllDonor();
    }

    @RequestMapping(value = "/donor/login", method = RequestMethod.POST)
    public ResponseEntity<Donor> getDonorId(@RequestBody User user)throws Exception {
        Donor donorId = donorService.validateDonorLogin(user.getUserId(), user.getPassword());
        if(donorId == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(donorId, HttpStatus.OK);
    }

    @RequestMapping(value = "/forget-password", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@RequestBody User user) throws Exception {
        String token = donorService.forgetPw(user.getUserId(), user.getEmail());
        if (token == null ){
            return new ResponseEntity<>("not found",HttpStatus.OK);
        }
        String resetUrl = "http://localhost:8080/eDonor/reset-password?token=" + token;
        sendEmail(user.getEmail(), resetUrl);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(@RequestBody Donor donor) throws Exception {
        String status =  donorService.updatePw(donor.getDonorId(),donor.getPassword());
        if (status == null ){
            return new ResponseEntity<>("Password Update Failed",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody PasswordResetToken passwordResetToken) throws Exception {
        String status =  donorService.changePw(passwordResetToken);
        if (status == null ){
            return new ResponseEntity<>("Password Update Failed",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendEmail(String to, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("edonor.noreply@gmail.com", "eDonor Support");
        helper.setTo(to);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        javaMailSender.send(message);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        javaMailSender.send(message);
    }
}
