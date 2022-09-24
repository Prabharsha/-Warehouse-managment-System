package view.tm;

import javafx.scene.control.Button;

public class ItemListTM {
    private String itemID;
    private String itemDescription;
    private int itemQNT;


    public ItemListTM() {
    }

    public ItemListTM(String itemID, String itemDescription, int itemQNT) {
        this.setItemID(itemID);
        this.setItemDescription(itemDescription);
        this.setItemQNT(itemQNT);
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

}
