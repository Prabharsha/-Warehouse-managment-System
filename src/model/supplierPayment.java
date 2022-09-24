package model;

public class supplierPayment {
    private String paymentID;
    private String supplierID;
    private String date;
    private Double amount;
    private Double amountPayed;

    public supplierPayment() {
    }

    public supplierPayment(String paymentID, String supplierID, String date, Double amount, Double amountPayed) {
        this.paymentID = paymentID;
        this.supplierID = supplierID;
        this.date = date;
        this.amount = amount;
        this.setAmountPayed(amountPayed);
    }

    public supplierPayment(String paymentID, String supplierID, String date, Double amount) {
        this.paymentID = paymentID;
        this.supplierID = supplierID;
        this.date = date;
        this.amount = amount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(Double amountPayed) {
        this.amountPayed = amountPayed;
    }
}
