package view.tm;

import javafx.scene.control.Button;

public class BanksTM {
    private String branchID;
    private String branchName;
    private String branchAddress;
    private String branchContact;
    private String branchEmail;
    private Button button;
    private Button button1;

    public BanksTM() {
    }

    public BanksTM(String branchID, String branchName, String branchAddress, String branchContact, String branchEmail, Button button, Button button1) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchContact = branchContact;
        this.branchEmail = branchEmail;
        this.button = button;
        this.button1 = button1;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(String branchContact) {
        this.branchContact = branchContact;
    }

    public String getBranchEmail() {
        return branchEmail;
    }

    public void setBranchEmail(String branchEmail) {
        this.branchEmail = branchEmail;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    @Override
    public String toString() {
        return "BanksTM{" +
                "branchID='" + branchID + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", branchContact='" + branchContact + '\'' +
                ", branchEmail='" + branchEmail + '\'' +
                ", button=" + button +
                ", button1=" + button1 +
                '}';
    }
}
