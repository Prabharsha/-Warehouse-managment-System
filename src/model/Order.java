package model;

import java.util.ArrayList;

public class Order {
    private String orderID;
    private String date;
    private String  branchID;
    private ArrayList <ItemDetails> items;

    public Order() {
    }

    public Order(String orderID, String date, String branchID, ArrayList<ItemDetails> items) {
        this.orderID = orderID;
        this.date = date;
        this.branchID = branchID;
        this.items = items;
    }

    public Order(String orderID, String date, String branchID) {
        this.orderID = orderID;
        this.date = date;
        this.branchID = branchID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public ArrayList<ItemDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", branchID='" + branchID + '\'' +
                ", items=" + items +
                '}';
    }
}
