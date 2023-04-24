package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/authorize")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Admin admin) throws ExecutionException, InterruptedException {
        return adminService.addAdmin(admin);
    }
    @GetMapping("/admin/{documentId}")
    public Admin getAdmin(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return adminService.getAdmin(documentId);
    }
    @PutMapping("/admin")
    public String updateAdmin(@RequestBody Admin admin) throws ExecutionException, InterruptedException {
        return adminService.updateAdmin(admin);
    }
    @DeleteMapping("/admin/{documentId}")
    public String deleteAdmin(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return adminService.deleteAdmin(documentId);
    }

    @GetMapping("/admin")
    public List<Admin> getAllStaff() throws ExecutionException, InterruptedException {
        return adminService.getAllAdmin();
    }
}