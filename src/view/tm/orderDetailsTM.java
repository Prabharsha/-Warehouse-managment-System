package view.tm;

public class orderDetailsTM {
    private String ItemID;
    private String itemDescription;
    private int itemQNT;

    public orderDetailsTM() {
    }

    public orderDetailsTM(String itemID, String itemDescription, int itemQNT) {
        ItemID = itemID;
        this.itemDescription = itemDescription;
        this.itemQNT = itemQNT;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
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
