package com.example.demo.controller;

import com.example.demo.entity.Record;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eDonor")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("/record")
    public String addRecord(@RequestBody Record record) throws ExecutionException, InterruptedException {
        return recordService.addRecord(record);
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
