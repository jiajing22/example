package com.example.demo.entity;

public class Record {
    private String recordId;
    private double weight;
    private double hbLevel;
    private double plateletAmt;
    private String blodPressure;
    private String recRemark;
    private String rStatus;
    private String staffId; //FK

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHbLevel() {
        return hbLevel;
    }

    public void setHbLevel(double hbLevel) {
        this.hbLevel = hbLevel;
    }

    public double getPlateletAmt() {
        return plateletAmt;
    }

    public void setPlateletAmt(double plateletAmt) {
        this.plateletAmt = plateletAmt;
    }

    public String getBlodPressure() {
        return blodPressure;
    }

    public void setBlodPressure(String blodPressure) {
        this.blodPressure = blodPressure;
    }

    public String getRecRemark() {
        return recRemark;
    }

    public void setRecRemark(String recRemark) {
        this.recRemark = recRemark;
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
