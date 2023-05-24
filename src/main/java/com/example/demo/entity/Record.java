package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;
import com.google.cloud.Timestamp;

public class Record extends GeneralEntity {
    private String recordId;
    private String donorIc;
    private String fullName;
    private String bloodSerialNo;
    private String donorType;
    private String status;
    private String regDate;
    private String lastDonateDate;
    private String bloodCentre;
    private double weight;
    private String bloodGroup;
    private String hbLevel;
    private double pAmount;
    private String bloodPressure;
    private String doubleCheckE;
    private String doubleCheckEOption;
    private Integer volume;
    private String isCUE;
    private String reason;
    private String deferralStatus;
    private String venepuncture;
    private String isGiven;
    private String timeStart;
    private String isTaken;
    private String timeEnd;
    private String remainingBarcodes;
    private String recRemark;
    private String rStatus;
    private String staffId; //FK

    public String getBloodCentre() {
        return bloodCentre;
    }

    public void setBloodCentre(String bloodCentre) {
        this.bloodCentre = bloodCentre;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getDonorIc() {
        return donorIc;
    }

    public void setDonorIc(String donorIc) {
        this.donorIc = donorIc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBloodSerialNo() {
        return bloodSerialNo;
    }

    public void setBloodSerialNo(String bloodSerialNo) {
        this.bloodSerialNo = bloodSerialNo;
    }

    public String getDonorType() {
        return donorType;
    }

    public void setDonorType(String donorType) {
        this.donorType = donorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLastDonateDate() {
        return lastDonateDate;
    }

    public void setLastDonateDate(String lastDonateDate) {
        this.lastDonateDate = lastDonateDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getHbLevel() {
        return hbLevel;
    }

    public void setHbLevel(String hbLevel) {
        this.hbLevel = hbLevel;
    }

    public double getpAmount() {
        return pAmount;
    }

    public void setpAmount(double pAmount) {
        this.pAmount = pAmount;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getDoubleCheckE() {
        return doubleCheckE;
    }

    public void setDoubleCheckE(String doubleCheckE) {
        this.doubleCheckE = doubleCheckE;
    }

    public String getDoubleCheckEOption() {
        return doubleCheckEOption;
    }

    public void setDoubleCheckEOption(String doubleCheckEOption) {
        this.doubleCheckEOption = doubleCheckEOption;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getIsCUE() {
        return isCUE;
    }

    public void setIsCUE(String isCUE) {
        this.isCUE = isCUE;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDeferralStatus() {
        return deferralStatus;
    }

    public void setDeferralStatus(String deferralStatus) {
        this.deferralStatus = deferralStatus;
    }

    public String getVenepuncture() {
        return venepuncture;
    }

    public void setVenepuncture(String venepuncture) {
        this.venepuncture = venepuncture;
    }

    public String getIsGiven() {
        return isGiven;
    }

    public void setIsGiven(String isGiven) {
        this.isGiven = isGiven;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(String isTaken) {
        this.isTaken = isTaken;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getRemainingBarcodes() {
        return remainingBarcodes;
    }

    public void setRemainingBarcodes(String remainingBarcodes) {
        this.remainingBarcodes = remainingBarcodes;
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

    @Override
    public String toString() {
        return "Record{" +
                "recordId='" + recordId + '\'' +
                ", donorIc='" + donorIc + '\'' +
                ", fullName='" + fullName + '\'' +
                ", bloodSerialNo='" + bloodSerialNo + '\'' +
                ", donorType='" + donorType + '\'' +
                ", status='" + status + '\'' +
                ", regDate=" + regDate +
                ", lastDonateDate=" + lastDonateDate +
                ", weight=" + weight +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", hbLevel=" + hbLevel +
                ", pAmount=" + pAmount +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", doubleCheckE='" + doubleCheckE + '\'' +
                ", doubleCheckEOption='" + doubleCheckEOption + '\'' +
                ", volume='" + volume + '\'' +
                ", isCUE='" + isCUE + '\'' +
                ", reason='" + reason + '\'' +
                ", deferralStatus='" + deferralStatus + '\'' +
                ", venepuncture='" + venepuncture + '\'' +
                ", isGiven=" + isGiven +
                ", timeStart=" + timeStart +
                ", isTaken=" + isTaken +
                ", timeEnd=" + timeEnd +
                ", remainingBarcodes=" + remainingBarcodes +
                ", recRemark='" + recRemark + '\'' +
                ", rStatus='" + rStatus + '\'' +
                ", staffId='" + staffId + '\'' +
                '}';
    }
}
