package com.example.demo.controller;

import com.example.demo.entity.BDCentre;
import com.example.demo.service.BDCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eDonor")
public class BDCentreController {

    @Autowired
    private BDCentreService bdCentreService;

    @PostMapping("/bdcentre")
    public ResponseEntity<String> addBDCentre(@RequestBody BDCentre bdCentre) throws ExecutionException, InterruptedException {
        String status = bdCentreService.addBDCentre(bdCentre);
        if( status != null ){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Added Failed",HttpStatus.NOT_FOUND);

    }
    @GetMapping("/bdcentre/{documentId}")
    public ResponseEntity<BDCentre> getBDCentre(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        BDCentre item = bdCentreService.getBDCentre(documentId);
        if (item != null){
            return new ResponseEntity<BDCentre>(item,HttpStatus.OK);
        }
        return new ResponseEntity<BDCentre>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/bdcentre/{documentId}")
    public ResponseEntity<String> updateBDCentre(@PathVariable String documentId, @RequestBody BDCentre bdCentre) throws ExecutionException, InterruptedException {
        String result = bdCentreService.updateBDCentre(bdCentre,documentId);
        if (result == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Exist");
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/bdcentre/{documentId}")
    public ResponseEntity<String> deleteBDCentre(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        String result = bdCentreService.deleteBDCentre(documentId);
        if (result == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record delete failed");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bdcentre")
    public List<BDCentre> getAllBDCentre() throws ExecutionException, InterruptedException {
        return bdCentreService.getAllBDCentre();
    }
}
