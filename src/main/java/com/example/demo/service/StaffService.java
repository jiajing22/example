package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Staff;
import com.example.demo.util.SHA256;
import com.example.demo.util.Util;
import com.example.demo.util.UtilException;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StaffService extends GeneralService {

    private static final String COLLECTION_NAME = "staff";
    private static final String USER_TYPE = "staff";

    public String addStaff (Staff staff) throws ExecutionException, InterruptedException, UtilException {
        String lastId = firestoreGetLastID(COLLECTION_NAME);
        staff.setDocumentId(Util.generateId("Staff", lastId));
        staff.setStaffId(staff.getDocumentId());
        staff.setUserId(staff.getDocumentId());
        staff.setPassword(SHA256.hash(staff.getPassword()));
        staff.setUserType(USER_TYPE);
        staff.setUserCreatedDate(Timestamp.now());
        return firestoreCreate(staff, COLLECTION_NAME);
    }

    public Staff getStaff(String documentId) throws ExecutionException, InterruptedException {
        return (Staff)firestoreGet(documentId, COLLECTION_NAME, Staff.class);
    }

    public String updateStaff (Staff staff) throws ExecutionException, InterruptedException {
        staff.setDocumentId(staff.getDocumentId());
        return firestoreUpdate(staff, COLLECTION_NAME);
    }

    public String adminUpdateStaff (Staff staff) throws ExecutionException, InterruptedException, UtilException {
        Staff staffInfo = getStaff(staff.getDocumentId());
        if (staffInfo != null){
            staffInfo.setFullName(staff.getFullName());
            staffInfo.setGender(staff.getGender());
            staffInfo.setEmail(staff.getEmail());
            staffInfo.setStaffPosition(staff.getStaffPosition());
            staffInfo.setDeptName(staff.getDeptName());
            staffInfo.setDeptArea(staff.getDeptArea());
            if ( !(staffInfo.getPassword().equals(staff.getPassword())) ){
                staffInfo.setPassword(SHA256.hash(staff.getPassword()));
            } else {
                staffInfo.setPassword(staff.getPassword());
            }

            return firestoreUpdate(staffInfo, COLLECTION_NAME);
        } else {
            return null;
        }
    }

    public String updateStaffPassword(Staff staff) throws ExecutionException, InterruptedException, UtilException {
        String hashed = SHA256.hash(staff.getPassword());
        return firestoreUpdatePassword(staff.getUserId(),hashed, COLLECTION_NAME);
    }

    public String deleteStaff(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<Staff> getAllStaff() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Staff.class, COLLECTION_NAME);
    }

    public String getUserIdByUsername(String username) throws ExecutionException, InterruptedException {
        return firestoreGetIdByUsername(username, COLLECTION_NAME);
    }

    public Staff getById(String userId) throws ExecutionException, InterruptedException {
        return (Staff)firestoreGet(userId, COLLECTION_NAME, Staff.class);
    }

    public String getUserIdByCredentials(String username, String password) throws ExecutionException, InterruptedException {
        return firestoreGetIdByCredentials(username, password, COLLECTION_NAME);
    }

    public Staff validateStaffLogin(String userName, String password) throws Exception {
        String id = getUserIdByCredentials(userName, SHA256.hash(password));

        if (id != null && !id.isEmpty()) {
            Staff staffWithIdOnly = new Staff();
            staffWithIdOnly.setUserId(id);
            Staff updateStaffLogin = (Staff)firestoreGet(id, COLLECTION_NAME, Staff.class);
            updateStaffLogin.setUserLastLoginDate(Timestamp.now());
            String updateLoginS = firestoreUpdate(updateStaffLogin, COLLECTION_NAME);
            return staffWithIdOnly;
        } else {
            return null;
        }

    }
}