package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;

import java.util.Date;

public class RegistrationForm extends GeneralEntity {
    private String regFormId;
    private String name;
    private String ic;
    private String dob;
    private Number age;
    private String ethnic;
    private String maritial;
    private String occupation;
    private String homeTel;
    private String hpTel;
    private String currentAd;
    private String state;
    private String postcode;
    private String formStatus;
    private String submitTime;
    private String donorId; //FK
    private String formFieldsId; //FK

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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getFormFieldsId() {
        return formFieldsId;
    }

    public void setFormFieldsId(String formFieldsId) {
        this.formFieldsId = formFieldsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getMaritial() {
        return maritial;
    }

    public void setMaritial(String maritial) {
        this.maritial = maritial;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getHpTel() {
        return hpTel;
    }

    public void setHpTel(String hpTel) {
        this.hpTel = hpTel;
    }

    public String getCurrentAd() {
        return currentAd;
    }

    public void setCurrentAd(String currentAd) {
        this.currentAd = currentAd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
