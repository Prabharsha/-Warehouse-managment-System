package model;

import view.tm.orderDetailsTM;

import java.util.ArrayList;

public class DispatchOrder {
    private String orderID;
    private String orderDate;
    private String dispatchedDate;
    private String branchID;
    private String deliveryTeamName;
    private ArrayList<orderDetailsTM> items;

    public DispatchOrder() {
    }

    public DispatchOrder(String orderID, String orderDate, String dispatchedDate, String branchID, String deliveryTeamName, ArrayList<orderDetailsTM> items) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.dispatchedDate = dispatchedDate;
        this.branchID = branchID;
        this.deliveryTeamName = deliveryTeamName;
        this.items = items;
    }

    public DispatchOrder(String orderID, String orderDate, String dispatchedDate, String branchID, String deliveryTeamName) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.dispatchedDate = dispatchedDate;
        this.branchID = branchID;
        this.deliveryTeamName = deliveryTeamName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderData() {
        return orderDate;
    }

    public void setOrderData(String orderData) {
        this.orderDate = orderData;
    }

    public String getDispatchedDate() {
        return dispatchedDate;
    }

    public void setDispatchedDate(String dispatchedData) {
        this.dispatchedDate = dispatchedData;
    }

    public String getbranchID() {
        return branchID;
    }

    public void setbranchID(String branchName) {
        this.branchID = branchName;
    }

    public String getDeliveryTeamName() {
        return deliveryTeamName;
    }

    public void setDeliveryTeamName(String deliveryTeamName) {
        this.deliveryTeamName = deliveryTeamName;
    }

    public ArrayList<orderDetailsTM> getItems() {
        return items;
    }

    public void setItems(ArrayList<orderDetailsTM> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "DispatchOrder{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", dispatchedDate='" + dispatchedDate + '\'' +
                ", branchID='" + branchID + '\'' +
                ", deliveryTeamName='" + deliveryTeamName + '\'' +
                ", items=" + items +
                '}';
    }
}

