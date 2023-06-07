package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.DonationHistory;
import com.example.demo.entity.Donor;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.util.SHA256;
import com.example.demo.util.Util;
import com.example.demo.util.UtilException;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class DonorService extends GeneralService {
    private static final String COLLECTION_NAME = "donor";
    private static final String USER_TYPE = "donor";

    public String addDonor (Donor donor) throws Exception {
        String lastID = firestoreGetLastID(COLLECTION_NAME);
        donor.setDocumentId(Util.generateId("Donor",lastID));
        donor.setDonorId(Util.generateId("Donor",lastID));
        donor.setPassword(SHA256.hash(donor.getPassword()));
        donor.setUserType(USER_TYPE);
        donor.setUserCreatedDate(Timestamp.now());
        List <DonationHistory> list = getHistoryRecordByIc(donor.getUserId());
        if (list == null){
            donor.setDonationTimes(0);
            donor.setDonorType("New Donor");
        } else {
            donor.setDonationTimes(list.size());
            donor.setDonorType("Regular/Repeat Donor");
        }

        donor.setIsVerified(false);
        String verificationToken = UUID.randomUUID().toString();
        donor.setVerifiedToken(verificationToken);

        String status = firestoreAddNewUser(donor, COLLECTION_NAME, donor.getUserId());

        if (status.equals("success")){
            sendVerification(donor.getEmail(), donor.getVerifiedToken());
        }

        return status;
    }

    public boolean validateToken (String token) {
        return findToken(COLLECTION_NAME, token);
    }

    public boolean updateVerificationStatus (String token) throws ExecutionException, InterruptedException {
        Donor donorInfo = getDonorInfoByToken(COLLECTION_NAME, token);
        if( donorInfo== null){
            return false;
        }
        donorInfo.setIsVerified(true);
        String update = firestoreUpdate(donorInfo,COLLECTION_NAME);
        return true;
    }

    public void sendVerification(String email, String token) throws MessagingException {
        String senderEmail = "edonor.noreply@gmail.com";
        String senderPassword = "vvhpqvhbwwplpwgq";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        String verificationLink = "https://backendproduction.up.railway.app/eDonor/verify?token=" + token;
        String content = "<p>Hello,</p>"
                + "<p>Please click on the link below to verify your email:</p>"
                + "<p><a href=\"" + verificationLink + "\">Activate now</a></p>"
                + "<br>";

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Email Verification");
        message.setContent(content, "text/html");

        Transport.send(message);
    }

    //Get By id
    public Donor getDonor(String donorId) throws ExecutionException, InterruptedException {
        return (Donor)firestoreGet(donorId, COLLECTION_NAME, Donor.class);
    }

    public String updateDonor (Donor donor) throws ExecutionException, InterruptedException {
        //1. Search current userId
        Donor donorInfo = getDonor(donor.getDonorId());
        if (donorInfo !=null ){
            donorInfo.setEmail(donor.getEmail());
            donorInfo.setPhone(donor.getPhone());
            donorInfo.setAddress(donor.getAddress());
            return firestoreUpdate(donorInfo, COLLECTION_NAME);
        } else{
            return null;
        }
    }

    public String deleteDonor(String donorId) throws ExecutionException, InterruptedException {
        return firestoreDelete(donorId, COLLECTION_NAME);
    }

    public List<Donor> getAllDonor() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Donor.class, COLLECTION_NAME);
    }

//    public Donor getById(String userId) throws ExecutionException, InterruptedException {
//        return (Donor)firestoreGet(userId, COLLECTION_NAME, Donor.class);
//    }

    public String getUserIdByUsername(String username) throws ExecutionException, InterruptedException {
        return firestoreGetIdByUsername(username, COLLECTION_NAME);
    }

    public String getUserIdByCredentials(String username, String password) throws ExecutionException, InterruptedException {
        return firestoreGetIdByCredentials(username, password, COLLECTION_NAME);
    }

    public Boolean isUserExist(String username, String email) throws ExecutionException, InterruptedException {
        return firestoreCheckUserEmail(username, email, COLLECTION_NAME);
    }

    public Donor findDonorById(String userId) throws ExecutionException, InterruptedException {
        return (Donor)firestoreGetByUserId(userId, COLLECTION_NAME, Donor.class);
    }

    public List<DonationHistory> getHistoryRecordByIc(String donorIc) throws ExecutionException, InterruptedException {
        return firestoreGetByIc(DonationHistory.class, "donationHistory", donorIc);
    }


    public String updatePw (String donorId, String password) throws ExecutionException, InterruptedException, UtilException {
        Donor donorInfo = getDonor(donorId);
        if (donorInfo !=null ){
            String hashedPw = SHA256.hash(password);
            donorInfo.setPassword(hashedPw);
            return firestoreUpdate(donorInfo, COLLECTION_NAME);
        } else{
            return null;
        }
    }

    public String changePw (PasswordResetToken passwordResetToken) throws ExecutionException, InterruptedException, UtilException {
        Donor getDonorByEmail = firestoreGetByEmail(passwordResetToken.getEmail(), COLLECTION_NAME);
        if( getDonorByEmail != null){
            String hash = SHA256.hash(passwordResetToken.getPassword());
            getDonorByEmail.setPassword(hash);
            PasswordResetToken update = getInfo(passwordResetToken.getToken());
            update.setIsExpired(true);
            String updateExpired = firestoreUpdate(update,"passwordResetToken");
            return firestoreUpdate(getDonorByEmail, COLLECTION_NAME);
        } else {
            return null;
        }
    }

    public PasswordResetToken getInfo(String token) throws ExecutionException, InterruptedException {
        return (PasswordResetToken)firestoreGet(token, "passwordResetToken", PasswordResetToken.class);
    }

    public String forgetPw (String username, String email) throws ExecutionException, InterruptedException {
        boolean check = isUserExist(username,email);
        if(check){
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setDocumentId(token);
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setCreated(Timestamp.now());
            resetToken.setIsExpired(false);
            String status = firestoreCreate(resetToken,"passwordResetToken");
            return token;
        } else {
            return null;
        }
    }

    public String matchToken (String token) throws ExecutionException, InterruptedException {
        PasswordResetToken checkToken = new PasswordResetToken();
        checkToken =  findPasswordToken("passwordResetToken", token);
        if (checkToken == null){
            return null;
        }
        return checkToken.getEmail();
    }

//    public List<Donor> findDonorById(String userId) throws ExecutionException, InterruptedException {
//        List<Donor> donors = firestoreGetByUserId(userId, COLLECTION_NAME, Donor.class, "D");
//        if (donors == null || donors.isEmpty()) {
//            return null;
//        }
//
//        List<Donor> result = new ArrayList<>();
//        for (Donor donorInfo : donors) {
//            Donor donorNeededInfo = new Donor();
//            donorNeededInfo.setUserId(donorInfo.getUserId());
//            donorNeededInfo.setFullName(donorInfo.getFullName());
//            donorNeededInfo.setBloodType(donorInfo.getBloodType());
//            result.add(donorNeededInfo);
//        }
//        return result;
//    }


    public Donor validateDonorLogin(String userUserName, String userPw) throws Exception {
        String id = null;
        id = getUserIdByUsername(userUserName);

        if (id != null && !id.isEmpty()) {

            String hashPassword = SHA256.hash(userPw);
            id = getUserIdByCredentials(userUserName, hashPassword);

            if (id != null && !id.isEmpty()) {
                Donor donorWithIdOnly = new Donor();
                Donor isVerified = getDonor(id);
                if (isVerified != null){
                    donorWithIdOnly.setUserId(id);
                    donorWithIdOnly.setIsVerified(isVerified.getIsVerified());
                    Donor updateDonorLogin = (Donor)firestoreGet(id, COLLECTION_NAME, Donor.class);
                    updateDonorLogin.setUserLastLoginDate(Timestamp.now());
                    String updateLoginD = firestoreUpdate(updateDonorLogin, COLLECTION_NAME);
                }
                return donorWithIdOnly;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
