package com.example.demo.entity;

import com.example.demo.base.BaseEntity;

public class User extends BaseEntity {

    private String fullName;
    private String gender;
    private String userType;

    public String getFullName() {
        return fullName;
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

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
