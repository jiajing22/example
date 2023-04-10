package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eDonor")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    public String addAppointment(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        return appointmentService.addAppointment(appointment);
    }
    @GetMapping("/appointment/{documentId}")
    public Appointment getAppointment(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return appointmentService.getAppointment(documentId);
    }
    @PutMapping("/appointment")
    public String updateAppointment(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        return appointmentService.updateAppointment(appointment);
    }
    @DeleteMapping("/appointment/{documentId}")
    public String deleteAppointment(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return appointmentService.deleteAppointment(documentId);
    }

    @GetMapping("/appointment")
    public List<Appointment> getAllAppointment() throws ExecutionException, InterruptedException {
        return appointmentService.getAllAppointment();
    }
}
