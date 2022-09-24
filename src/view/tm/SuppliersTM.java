package view.tm;

import javafx.scene.control.Button;

public class SuppliersTM {
    private String supplierID;
    private String supplierName;
    private String supplierAddress;
    private String supplierContact;
    private Double amountToBePayed;
    private Button button1;
    private Button button2;

    public SuppliersTM() {
    }

    public SuppliersTM(String supplierID, String supplierName, String supplierAddress, String supplierContact, Double amountToBePayed, Button button1, Button button2) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierContact = supplierContact;
        this.amountToBePayed = amountToBePayed;
        this.button1 = button1;
        this.button2 = button2;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public Double getAmountToBePayed() {
        return amountToBePayed;
    }

    public void setAmountToBePayed(Double amountToBePayed) {
        this.amountToBePayed = amountToBePayed;
    }

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }
}
