package model;

public class Item {
    private String itemID;
    private String itemDescription;
    private int itemQNT;
    private Double itemUnitePrice;
    private String supplierID;

    public Item() {
    }

    public Item(String itemID, String itemDescription, int itemQNT, Double itemUnitePrice, String supplierID) {
        this.setItemID(itemID);
        this.setItemDescription(itemDescription);
        this.setItemQNT(itemQNT);
        this.setItemUnitePrice(itemUnitePrice);
        this.setSupplierID(supplierID);
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemQNT() {
        return itemQNT;
    }

    public void setItemQNT(int itemQNT) {
        this.itemQNT = itemQNT;
    }

    public Double getItemUnitePrice() {
        return itemUnitePrice;
    }

    public void setItemUnitePrice(Double itemUnitePrice) {
        this.itemUnitePrice = itemUnitePrice;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
}
