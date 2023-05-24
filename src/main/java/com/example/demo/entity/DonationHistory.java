package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;
import com.example.demo.base.GeneralService;

public class DonationHistory extends GeneralEntity {
    private String historyId;
    private String bloodSerialNo;
    private String donateDate;
    private Integer amount;
    private String dHospital;
    private String recRemark;
    private String donorIc;
    private String recordId;

    public String getRecRemark() {
        return recRemark;
    }

    public void setRecRemark(String recRemark) {
        this.recRemark = recRemark;
    }

    public String getDonorId() {
        return donorIc;
    }

    public void setDonorId(String donorIc) {
        this.donorIc = donorIc;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getBloodSerialNo() {
        return bloodSerialNo;
    }

    public void setBloodSerialNo(String bloodSerialNo) {
        this.bloodSerialNo = bloodSerialNo;
    }

    public String getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(String donateDate) {
        this.donateDate = donateDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getdHospital() {
        return dHospital;
    }

    public void setdHospital(String dHospital) {
        this.dHospital = dHospital;
    }

    @Override
    public String toString() {
        return "DonationHistory{" +
                "historyId='" + historyId + '\'' +
                ", bloodSerialNo='" + bloodSerialNo + '\'' +
                ", donateDate='" + donateDate + '\'' +
                ", amount=" + amount +
                ", dHospital='" + dHospital + '\'' +
                ", recRemark='" + recRemark + '\'' +
                ", donorIc='" + donorIc + '\'' +
                ", recordId='" + recordId + '\'' +
                '}';
    }
}
