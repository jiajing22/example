package com.example.demo.controller;

import com.example.demo.entity.BDCentre;
import com.example.demo.service.BDCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eDonor")
public class BDCentreController {

    @Autowired
    private BDCentreService bdCentreService;

    @PostMapping("/bdcentre")
    public String addBDCentre(@RequestBody BDCentre bdCentre) throws ExecutionException, InterruptedException {
        return bdCentreService.addBDCentre(bdCentre);
    }
    @GetMapping("/bdcentre/{documentId}")
    public BDCentre getBDCentre(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return bdCentreService.getBDCentre(documentId);
    }
    @PutMapping("/bdcentre")
    public String updateBDCentre(@RequestBody BDCentre bdCentre) throws ExecutionException, InterruptedException {
        return bdCentreService.updateBDCentre(bdCentre);
    }
    @DeleteMapping("/bdcentre/{documentId}")
    public String deleteBDCentre(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return bdCentreService.deleteBDCentre(documentId);
    }

    @GetMapping("/bdcentre")
    public List<BDCentre> getAllBDCentre() throws ExecutionException, InterruptedException {
        return bdCentreService.getAllBDCentre();
    }
}
