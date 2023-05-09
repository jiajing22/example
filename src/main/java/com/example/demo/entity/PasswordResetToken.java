package com.example.demo.entity;

import com.example.demo.base.GeneralEntity;
import com.google.cloud.Timestamp;

public class PasswordResetToken extends GeneralEntity {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    private String email;
    private Timestamp created;
}
