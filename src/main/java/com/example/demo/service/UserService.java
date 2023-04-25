package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Donor;
import com.example.demo.entity.Staff;
import com.example.demo.entity.User;
import com.example.demo.util.SHA256;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService extends GeneralService {

    private static final String COLLECTION_NAME = "users";

//    public String createUser (User user) throws ExecutionException, InterruptedException {
//        user.setDocumentId(user.getDocumentId());
//        return firestoreCreate(user, COLLECTION_NAME);
//    }
//
//    public User getUser(String userId) throws ExecutionException, InterruptedException {
//        return (User)firestoreGet(userId, COLLECTION_NAME, User.class);
//    }
//
//    public String updateUser (User user) throws ExecutionException, InterruptedException {
//        user.setDocumentId(user.getDocumentId());
//        return firestoreUpdate(user, COLLECTION_NAME);
//    }
//
//    public String deleteUser(String userId) throws ExecutionException, InterruptedException {
//        return firestoreDelete(userId, COLLECTION_NAME);
//    }

    public List<User> getAllUser() throws ExecutionException, InterruptedException {
        return firestoreGetAll(User.class, COLLECTION_NAME);
    }

    public User getById(String userId) throws ExecutionException, InterruptedException {
        return (User)firestoreGet(userId, COLLECTION_NAME, User.class);
    }

    public String getUserIdByUsername(String username) throws ExecutionException, InterruptedException {
        return firestoreGetIdByUsername(username, COLLECTION_NAME);
    }

    public String getUserIdByCredentials(String username, String password) throws ExecutionException, InterruptedException {
        return firestoreGetIdByCredentials(username, password, COLLECTION_NAME);
    }


//    public String validateUserLogin(String userUserName, String userPw) throws Exception {
//
//        //1. Search id by username
//        String id = getUserIdByUsername(userUserName);
//        System.out.println(id);
//
//        //2. Check if id exists
//        if (id != null && !id.isEmpty()) {
//
//            //2.1 Check account status
//            User user = getById(id);
////            System.out.println(user.toString());
//
////            String status = user.getUserStatus();
//            // check when username exists
////            if (Constant.USER_STATUS_PENDING.equals(status)) {
////                throw new Exception("Your account is still waiting for approval.");
////
////            } else if (Constant.USER_STATUS_LOCKED.equals(status) || user.getUserNumberOfRetries() >= 5) {
////                throw new Exception("You have entered an incorrect code too many time and your account is temporarily locked." +
////                        " Please try again later.");
////            }
//
//            //3. Compare with the hash password
////            String hashPassword = SHA1.hash(userPw);
//            id = getUserIdByCredentials(userUserName, userPw);
//            if (id != null && !id.isEmpty()) {
//                // login success
//
//                user.setUserLastLoginDate(Timestamp.now());
//                user.setUserType("test");
////                user.setUserNumberOfRetries(0);
////                updateUser(user); //audit trail
////                User userWithIdOnly = new User();
////                userWithIdOnly.setUserId(id);
//                return id;
//
//            } else {
//                // when username exist with wrong pw
////                int retries = user.getUserNumberOfRetries() + 1;
////                if (retries == 5) {
////                    user.setUserStatus(Constant.USER_STATUS_LOCKED);
////                }
////                user.setUserNumberOfRetries(retries);
////                updateUser(user);
//                throw new Exception("Incorrect username or password.");
//            }
//
//        } else {
//            throw new Exception("Incorrect username or password.");
//        }
//
//    }

    public String validateUserLogin(String userUserName, String userPw) {
        String id = null;

        try {
            id = getUserIdByUsername(userUserName);

            if (id != null && !id.isEmpty()) {
                User user = getById(id);
                String hashPassword = SHA256.hash(userPw);
                System.out.println("Hashed" + hashPassword);
                id = getUserIdByCredentials(userUserName, hashPassword);
                String userType = user.getUserType();

                if (id != null && !id.isEmpty()) {

                    if( userType.equals("donor")){
                        Donor updateDonorLogin = (Donor)firestoreGet(id, COLLECTION_NAME, Donor.class);
                        updateDonorLogin.setUserLastLoginDate(Timestamp.now());
                        String updateLoginD = firestoreUpdate(updateDonorLogin, COLLECTION_NAME);

                    } else if (userType.equals("staff")) {
                        Staff updateStaffLogin = (Staff)firestoreGet(id, COLLECTION_NAME, Staff.class);
                        updateStaffLogin.setUserLastLoginDate(Timestamp.now());
                        String updateLoginS = firestoreUpdate(updateStaffLogin, COLLECTION_NAME);
                    }
                    return id;
                } else {
                    return "Incorrect username or password.";
                }
            } else {
                return "Incorrect username or password.";
            }
        } catch (Exception e) {
            return "An error occurred while validating user login: " + e.getMessage();
        }
    }
}
