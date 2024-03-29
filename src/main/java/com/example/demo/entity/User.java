package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;
import com.google.cloud.Timestamp;

import java.util.Date;

public class User extends GeneralEntity {

    private String userId; //userIc
    private String password;
    private String fullName;
    private String gender;
    private String userType;
    private String email;
    private String address;
    private Timestamp userLastLoginDate;
    private Timestamp userCreatedDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public Timestamp getUserLastLoginDate() {
        return userLastLoginDate;
    }

    public void setUserLastLoginDate(Timestamp userLastLoginDate) {
        this.userLastLoginDate = userLastLoginDate;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(Timestamp userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", userType='" + userType + '\'' +
                ", userLastLoginDate=" + userLastLoginDate +
                '}';
    }
}
