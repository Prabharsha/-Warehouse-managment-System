package view.tm;

public class SuppliersPayTM {
    private String supplierID;
    private String SupplierName;
    private String supplierAddress;
    private String supplierContact;
    private Double amountToBePayed;

    public SuppliersPayTM() {
    }

    public SuppliersPayTM(String supplierID, String supplierName, String supplierAddress, String supplierContact, Double amountToBePayed) {
        this.supplierID = supplierID;
        SupplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierContact = supplierContact;
        this.amountToBePayed = amountToBePayed;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
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
}
