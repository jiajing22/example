package com.example.demo.controller;

import com.example.demo.entity.Staff;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class StaffController {

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
}
