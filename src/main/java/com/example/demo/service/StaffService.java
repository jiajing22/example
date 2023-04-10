package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Staff;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StaffService extends GeneralService {

    private static final String COLLECTION_NAME = "users";
    private static final String USER_TYPE = "staff";

    public String addStaff (Staff staff) throws ExecutionException, InterruptedException {
        staff.setDocumentId(staff.getDocumentId());
        staff.setUserType(USER_TYPE);
//        staff.setUserType(USER_TYPE);
//        staff.setDate(Timestamp.now());
//        System.out.println("AA");
//        System.out.println(Timestamp.now().toDate());
        return firestoreCreate(staff, COLLECTION_NAME);
    }

    public Staff getStaff(String documentId) throws ExecutionException, InterruptedException {
        return (Staff)firestoreGet(documentId, COLLECTION_NAME, Staff.class);
    }

    public String updateStaff (Staff staff) throws ExecutionException, InterruptedException {
        staff.setDocumentId(staff.getDocumentId());
        return firestoreUpdate(staff, COLLECTION_NAME);
    }

    public String deleteStaff(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<Staff> getAllStaff() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Staff.class, COLLECTION_NAME, "ST");
    }
}