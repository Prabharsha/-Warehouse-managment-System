package model;

import java.util.Date;

public class supplierPayHistory {
    private String paymentID;
    private String supplierID;
    private Date date;
    private Double paymentAmount;

    public supplierPayHistory() {
    }

    public supplierPayHistory(String paymentID, String supplierID, Date date, Double paymentAmount) {
        this.setPaymentID(paymentID);
        this.setSupplierID(supplierID);
        this.setDate(date);
        this.setPaymentAmount(paymentAmount);
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
