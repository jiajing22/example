package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;
import com.example.demo.base.GeneralService;

import java.util.Date;

public class DonationHistory extends GeneralEntity {
    private String historyId;
    private String bloodSerialNo;
    private Date donateDate;
    private Integer amount;
    private String dHospital;
    private String recRemark;
    private String donorId;
    private String recordId;

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

    public Date getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(Date donateDate) {
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
}
