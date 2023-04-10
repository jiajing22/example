package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Record;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RecordService extends GeneralService {
    private static final String COLLECTION_NAME = "record";

    public String addRecord (Record record) throws ExecutionException, InterruptedException {
        record.setDocumentId(record.getDocumentId());
        return firestoreCreate(record, COLLECTION_NAME);
    }

    public Record getRecord(String documentId) throws ExecutionException, InterruptedException {
        return (Record)firestoreGet(documentId, COLLECTION_NAME, Record.class);
    }

    public String updateRecord (Record record) throws ExecutionException, InterruptedException {
        record.setDocumentId(record.getDocumentId());
        return firestoreUpdate(record, COLLECTION_NAME);
    }

    public String deleteRecord(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<Record> getAllRecord() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Record.class, COLLECTION_NAME);
    }
}
