package com.example.smartbilling.Model;

public class MeterBill {
    String socityName;
    String username;
    String usermeterID;
    String currentunit;
    String previousunit;
    String usedunit;
    String unitprice;
    String meantense;

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    String BillDate;

    public String getSocityName() {
        return socityName;
    }

    public void setSocityName(String socityName) {
        this.socityName = socityName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsermeterID() {
        return usermeterID;
    }

    public void setUsermeterID(String usermeterID) {
        this.usermeterID = usermeterID;
    }

    public String getCurrentunit() {
        return currentunit;
    }

    public void setCurrentunit(String currentunit) {
        this.currentunit = currentunit;
    }

    public String getPreviousunit() {
        return previousunit;
    }

    public void setPreviousunit(String previousunit) {
        this.previousunit = previousunit;
    }

    public String getUsedunit() {
        return usedunit;
    }

    public void setUsedunit(String usedunit) {
        this.usedunit = usedunit;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public String getMeantense() {
        return meantense;
    }

    public void setMeantense(String meantense) {
        this.meantense = meantense;
    }

    public String getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(String totalbill) {
        this.totalbill = totalbill;
    }

    String totalbill;

}
