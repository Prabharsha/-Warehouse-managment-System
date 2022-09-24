package view.tm;

import javafx.scene.control.Button;

import java.util.Date;

public class supplierPaymentsTM {
    private String paymentID;
    private String supplierID;
    private Date date;
    private Double amount;
    private Button buttonMark;

    public supplierPaymentsTM() {
    }

    public supplierPaymentsTM(String paymentID, String supplierID, Date date, Double amount, Button buttonMark) {
        this.setPaymentID(paymentID);
        this.setSupplierID(supplierID);
        this.setDate(date);
        this.setAmount(amount);
        this.setButtonMark(buttonMark);
    }

    public Button getButtonMark() {
        return buttonMark;
    }

    public void setButtonMark(Button buttonMark) {
        this.buttonMark = buttonMark;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
