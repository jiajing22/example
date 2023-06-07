package com.example.demo.controller;

import com.example.demo.entity.FormData;
import com.example.demo.entity.RegistrationForm;
import com.example.demo.service.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/eDonor")
public class RegistrationFormController {
    @Autowired
    private RegistrationFormService registrationFormService;

    @PostMapping("/registration")
    public ResponseEntity<String> addForm(@RequestBody FormData formData) throws ExecutionException, InterruptedException {
        String status = registrationFormService.addRegForm(formData);
        if (status == null){
            return new ResponseEntity<>("Record Added Failed", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Added Successfully",HttpStatus.OK);
    }
    @GetMapping("/registration/{documentId}")
    public RegistrationForm getForm(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return registrationFormService.getRegForm(documentId);
    }
    @PostMapping("/registration-update")
    public String updateForm(@RequestBody RegistrationForm form) throws ExecutionException, InterruptedException {
        return registrationFormService.updateRegForm(form);
    }
    @DeleteMapping("/registration/{documentId}")
    public String deleteForm(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return registrationFormService.deleteRegForm(documentId);
    }
//    @GetMapping("/registration")
//    public List<RegistrationForm> getAllForm() throws ExecutionException, InterruptedException {
//        return registrationFormService.getAllRegForm();
//    }

    @GetMapping("/registration-form/{documentId}") //documentid = donorIc = donorId
    public ResponseEntity<List<FormData>> getFormByIc(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        List <FormData> formData = registrationFormService.getRegFormByIc(documentId);
        if( formData == null ){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(formData, HttpStatus.OK);
    }

    @GetMapping("/registration-form")
    public ResponseEntity<List<FormData>> getAllFormData() throws ExecutionException, InterruptedException {
        List <FormData> formData = registrationFormService.getAllRegForm();
        if(formData.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(formData, HttpStatus.OK);
    }
}
