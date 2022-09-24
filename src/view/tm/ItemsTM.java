package view.tm;

import javafx.scene.control.Button;

public class ItemsTM {
    private String itemID;
    private String itemDescription;
    private int itemQNT;
    private Double itemUnitePrice;
    private String supplierID;
    private Button button1;
    private Button button2;

    public ItemsTM() {
    }

    public ItemsTM(String itemID, String itemDescription, int itemQNT, Double itemUnitePrice, String supplierID, Button button1, Button button2) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.itemQNT = itemQNT;
        this.itemUnitePrice = itemUnitePrice;
        this.supplierID = supplierID;
        this.button1 = button1;
        this.button2 = button2;
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
