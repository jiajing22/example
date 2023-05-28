package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;
import com.google.cloud.Timestamp;

public class Appointment extends GeneralEntity {
    private String appointmentId;
    private String appmntDate;
    private String timeslot;
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

    public String getAppmntDate() {
        return appmntDate;
    }

    public void setAppmntDate(String appmntDate) {
        this.appmntDate = appmntDate;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
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
