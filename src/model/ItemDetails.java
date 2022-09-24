package model;

import javafx.scene.control.Button;

public class ItemDetails {
    private String itemID;
    private String itemDescription;
    private int itemQNT;



    public ItemDetails() {
    }

    public ItemDetails(String itemID, String itemDescription, int itemQNT) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.itemQNT = itemQNT;
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

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemID='" + itemID + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemQNT=" + itemQNT +
                '}';
    }
}
