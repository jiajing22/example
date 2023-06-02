package com.example.demo.entity;

public class Donor extends User{

    private String donorId;
    private String donorType;
    private String bloodType;
    private String address;
    private String phone;
    private Boolean isVerified;
    private String verifiedToken;
    private Integer donationTimes;

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getDonorType() {
        return donorType;
    }

    public void setDonorType(String donorType) {
        this.donorType = donorType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDonationTimes() {
        return donationTimes;
    }

    public void setDonationTimes(Integer donationTimes) {
        this.donationTimes = donationTimes;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean verified) {
        this.isVerified = verified;
    }

    public String getVerifiedToken() {
        return verifiedToken;
    }

    public void setVerifiedToken(String verifiedToken) {
        this.verifiedToken = verifiedToken;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donorId='" + donorId + '\'' +
                ", donorType='" + donorType + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isVerified=" + isVerified +
                ", verifiedToken='" + verifiedToken + '\'' +
                ", donationTimes=" + donationTimes +
                '}';
    }
}
