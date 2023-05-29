package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.DonationHistory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DonationHistoryService extends GeneralService {
    private static final String COLLECTION_NAME = "donationHistory";

    public String addHistory (DonationHistory history) throws Exception {
//        String lastID = firestoreGetLastID(COLLECTION_NAME);
//        history.setDocumentId(Util.generateId("History",lastID));
//        history.setHistoryId(Util.generateId("History",lastID));
        String status = firestoreCreate(history, COLLECTION_NAME);

        if( status != null){
            return "History insert success!";
        }
        return null;
    }

    public DonationHistory getHistory(String documentId) throws ExecutionException, InterruptedException {
        return (DonationHistory)firestoreGet(documentId, COLLECTION_NAME, DonationHistory.class);
    }

    public String updateHistory (DonationHistory history) throws ExecutionException, InterruptedException {
        history.setDocumentId(history.getDocumentId());
        return firestoreUpdate(history, COLLECTION_NAME);
    }

    public String deleteHistory(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<DonationHistory> getAllHistory() throws ExecutionException, InterruptedException {
        return firestoreGetAll(DonationHistory.class, COLLECTION_NAME);
    }

    public List<DonationHistory> getHistoryRecordByIc(String donorIc) throws ExecutionException, InterruptedException {
        return firestoreGetByIc(DonationHistory.class, COLLECTION_NAME, donorIc);
    }
}
