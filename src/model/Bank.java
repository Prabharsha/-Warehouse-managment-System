package model;

public class Bank {
    private String branchID;
    private String branchName;
    private String branchAddress;
    private String branchContact;
    private String branchEmail;

    public Bank() {
    }

    public Bank(String branchID, String branchName, String branchAddress, String branchContact, String branchEmail) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchContact = branchContact;
        this.branchEmail = branchEmail;
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
}
