package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Donor;
import com.example.demo.entity.User;
import com.example.demo.service.AdminService;
import com.example.demo.util.UtilException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/authorize")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Admin admin) throws ExecutionException, InterruptedException, UtilException {
        return adminService.addAdmin(admin);
    }
    @PostMapping("/admin-get")
    public ResponseEntity<Admin> getAdmin(@RequestBody User user) throws ExecutionException, InterruptedException {
        Admin admin = adminService.getAdmin(user.getDocumentId());
        if(admin == null){
            return new ResponseEntity<Admin>(HttpStatus.OK);
        }
        return new ResponseEntity<Admin>(admin, HttpStatus.OK);
    }

    @PutMapping("/admin-update")
    public String updateAdmin(@RequestBody Admin admin) throws ExecutionException, InterruptedException, UtilException {
        return adminService.updateAdmin(admin);
    }

    @PutMapping("/admin-update-credential")
    public String updatePw(@RequestBody Admin admin) throws ExecutionException, InterruptedException, UtilException {
        return adminService.updatePassword(admin);
    }

    @DeleteMapping("/admin/{documentId}")
    public String deleteAdmin(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return adminService.deleteAdmin(documentId);
    }

    @GetMapping("/admin")
    public List<Admin> getAllStaff() throws ExecutionException, InterruptedException {
        return adminService.getAllAdmin();
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public ResponseEntity<?> getStaffId(@RequestBody User user)throws Exception {
        Admin adminId = adminService.validateAdminLogin(user.getUserId(), user.getPassword());
        if (adminId == null){
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(adminId, HttpStatus.OK);
    }
}
