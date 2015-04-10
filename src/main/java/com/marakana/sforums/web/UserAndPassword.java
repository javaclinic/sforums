package com.marakana.sforums.web;

import java.io.Serializable;

import com.marakana.sforums.domain.User;

public class UserAndPassword implements Serializable {

    private static final long serialVersionUID = 7831553513088258420L;

    private final User user;
    private String password;
    private String passwordVerification;

    public UserAndPassword(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerification() {
        return passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }

    public boolean isPasswordVerificationSet() {
        return this.passwordVerification != null && !this.passwordVerification.isEmpty();
    }

    public boolean isPasswordSet() {
        return this.password != null && !this.password.isEmpty();
    }

    public boolean isPasswordVerified() {
        return this.isPasswordSet() && this.password.equals(this.passwordVerification);
    }

}
