package com.example.demo.entity;

import com.google.cloud.Timestamp;

public class Appointment {
    private String appointmentId;
    private Timestamp appmntDate;
    private Timestamp timeslot;
    private String appmntLocation;
    private String aStatus;
    private String donorId;

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Timestamp getAppmntDate() {
        return appmntDate;
    }

    public void setAppmntDate(Timestamp appmntDate) {
        this.appmntDate = appmntDate;
    }

    public Timestamp getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timestamp timeslot) {
        this.timeslot = timeslot;
    }

    public String getAppmntLocation() {
        return appmntLocation;
    }

    public void setAppmntLocation(String appmntLocation) {
        this.appmntLocation = appmntLocation;
    }

    public String getaStatus() {
        return aStatus;
    }

    public void setaStatus(String aStatus) {
        this.aStatus = aStatus;
    }
}
