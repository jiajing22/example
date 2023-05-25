package com.example.demo.controller;

import com.example.demo.entity.DonationHistory;
import com.example.demo.service.DonationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eDonor")
public class DonationHistoryController {

    @Autowired
    private DonationHistoryService donationHistoryService;

//    @PostMapping("/history")
//    public String addHistory(@RequestBody DonationHistory history) throws Exception {
//        return donationHistoryService.addHistory(history);
//    }

    @GetMapping("/history/{documentId}")
    public DonationHistory getHistory(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return donationHistoryService.getHistory(documentId);
    }
    @PutMapping("/history")
    public String updateHistory(@RequestBody DonationHistory history) throws ExecutionException, InterruptedException {
        return donationHistoryService.updateHistory(history);
    }
    @DeleteMapping("/history/{documentId}")
    public String deleteHistory(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return donationHistoryService.deleteHistory(documentId);
    }
    @GetMapping("/history")
    public List<DonationHistory> getAllHistory() throws ExecutionException, InterruptedException {
        return donationHistoryService.getAllHistory();
    }

    @GetMapping("/history/donation/{donorIc}")
    public ResponseEntity<List<DonationHistory>> getUserAllHistory(@PathVariable String donorIc) throws ExecutionException, InterruptedException {
        List <DonationHistory> list = donationHistoryService.getHistoryRecordByIc(donorIc);

        if ( list == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<DonationHistory>>(list, HttpStatus.OK);
    }
}
