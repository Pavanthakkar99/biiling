package com.example.smartbilling.Model;

import java.util.Date;

public class MeterReading {
    String meterId;
    String contactNo;
    String readingPoint;
    String userName;
    String date;
    String userId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getReadingPoint() {
        return readingPoint;
    }

    public void setReadingPoint(String readingPoint) {
        this.readingPoint = readingPoint;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}



