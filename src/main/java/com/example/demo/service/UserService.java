//package com.example.demo.service;
//
//import com.example.demo.base.GeneralService;
//import com.example.demo.entity.User;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//@Service
//public class UserService extends GeneralService {
//
//    private static final String COLLECTION_NAME = "users";
//
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
//
//    public List<User> getAllUser() throws ExecutionException, InterruptedException {
//        return firestoreGetAll(User.class, COLLECTION_NAME);
//    }
//}
