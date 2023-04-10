package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;

import java.util.Date;

public class RegistrationForm extends GeneralEntity {
    private String regFormId;
    private String formStatus;
    private Date submitTime;
    private String donorId; //FK

    public String getRegFormId() {
        return regFormId;
    }

    public void setRegFormId(String regFormId) {
        this.regFormId = regFormId;
    }

    public String getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }
}
