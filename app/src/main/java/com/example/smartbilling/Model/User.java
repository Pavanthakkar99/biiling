package com.example.smartbilling.Model;

public class User {
    String userName;
    String mobileNumber;
    String emailId;
    String password;
    String meterId;

    public User(String userName, String mobileNumber, String emailId, String password,String meterId) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.password = password;
        this.meterId=meterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }
}
