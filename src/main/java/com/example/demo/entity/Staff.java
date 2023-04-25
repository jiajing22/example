package com.example.demo.entity;


import com.google.cloud.Timestamp;

public class Staff extends User{
    private String staffId;
    private String staffPosition;
    private String deptName;
    private String deptArea;
//    private String userId; //FK

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptArea() {
        return deptArea;
    }

    public void setDeptArea(String deptArea) {
        this.deptArea = deptArea;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
}
