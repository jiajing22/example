package com.example.demo.controller;

import com.example.demo.entity.Record;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eDonor")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("/record")
    public ResponseEntity<String> addRecord(@RequestBody Record record) throws Exception {
        String addRecord = recordService.addRecord(record);
        if ( addRecord != null ){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Added Failed",HttpStatus.OK);
    }
    @GetMapping("/record/{documentId}")
    public Record getRecord(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return recordService.getRecord(documentId);
    }
    @PutMapping("/record")
    public String updateRecord(@RequestBody Record record) throws ExecutionException, InterruptedException {
        return recordService.updateRecord(record);
    }
    @DeleteMapping("/record/{documentId}")
    public String deleteRecord(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return recordService.deleteRecord(documentId);
    }
    @GetMapping("/record")
    public List<Record> getAllRecord() throws ExecutionException, InterruptedException {
        return recordService.getAllRecord();
    }
}
