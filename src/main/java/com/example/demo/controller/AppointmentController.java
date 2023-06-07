package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/eDonor")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        String status = appointmentService.addAppointment(appointment);
        if (status.equals("success")){
            return new ResponseEntity<>("Appointment Booked Successfully!",HttpStatus.OK);
        }else if (status.equals("exist")){
            return new ResponseEntity<>("You already Booked Appointment at this day",HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Appointment Booked Failed",HttpStatus.OK);

        }
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

    @PostMapping("/appointment-list")
    public List<Appointment> getAppointmentByIc(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        return appointmentService.getAppointmentListByIc(appointment.getDonorId());
    }
}
