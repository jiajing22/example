package com.example.demo.controller;

import com.example.demo.entity.RegistrationForm;
import com.example.demo.service.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eDonor")
public class RegistrationFormController {
    @Autowired
    private RegistrationFormService registrationFormService;

    @PostMapping("/registration")
    public String addForm(@RequestBody RegistrationForm form) throws ExecutionException, InterruptedException {
        return registrationFormService.addRegForm(form);
    }
    @GetMapping("/registration/{documentId}")
    public RegistrationForm getForm(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return registrationFormService.getRegForm(documentId);
    }
    @PutMapping("/registration")
    public String updateForm(@RequestBody RegistrationForm form) throws ExecutionException, InterruptedException {
        return registrationFormService.updateRegForm(form);
    }
    @DeleteMapping("/registration/{documentId}")
    public String deleteForm(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return registrationFormService.deleteRegForm(documentId);
    }
    @GetMapping("/registration")
    public List<RegistrationForm> getAllForm() throws ExecutionException, InterruptedException {
        return registrationFormService.getAllRegForm();
    }
}
