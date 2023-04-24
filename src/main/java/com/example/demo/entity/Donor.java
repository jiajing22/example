package com.example.demo.entity;

public class Donor extends User{

    private String donorId;
    private String donorType;
    private String bloodType;
    private String address;

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

    @Override
    public String toString() {
        return "Donor{" +
                super.toString()+
                "donorId='" + donorId + '\'' +
                ", donorType='" + donorType + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
