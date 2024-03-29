package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;

public class BDCentre extends GeneralEntity {
    private String centreId;
    private String centreName;
    private String centreState;
    private String centrePhone;
    private String cAddress;
    private String workHourStart;
    private String workHourEnd;

    public String getCentreId() {
        return centreId;
    }

    public void setCentreId(String centreId) {
        this.centreId = centreId;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getCentreState() {
        return centreState;
    }

    public void setCentreState(String centreState) {
        this.centreState = centreState;
    }

    public String getCentrePhone() {
        return centrePhone;
    }

    public void setCentrePhone(String centrePhone) {
        this.centrePhone = centrePhone;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getWorkHourStart() {
        return workHourStart;
    }

    public void setWorkHourStart(String workHourStart) {
        this.workHourStart = workHourStart;
    }

    public String getWorkHourEnd() {
        return workHourEnd;
    }

    public void setWorkHourEnd(String workHourEnd) {
        this.workHourEnd = workHourEnd;
    }
}
