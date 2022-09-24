package view.tm;
import java.util.Date;

public class supplierpaymentsHistory {
    private String paymentID;
    private String supplierID;
    private Date date;
    private Double paymentAmount;

    public supplierpaymentsHistory() {
    }

    public supplierpaymentsHistory(String paymentID, String supplierID, Date date, Double paymentAmount) {
        this.paymentID = paymentID;
        this.supplierID = supplierID;
        this.date = date;
        this.paymentAmount = paymentAmount;
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
