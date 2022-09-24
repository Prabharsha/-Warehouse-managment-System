package logics;

import DBUtil.DbConnection;
import model.DispatchOrder;
import model.ItemDetails;
import model.Order;
import view.tm.orderDetailsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public static String getOrderID() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT orderID FROM `orderID` ORDER BY orderID DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempID = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempID = tempID + 1;
            if (tempID <= 9) {
                return "O-00" + tempID;
            } else if (tempID <= 99) {
                return "O-0" + tempID;
            } else {
                return "O-" + tempID;
            }

        } else {
            return "O-001";
        }
    }

    public static List<Order> getAllOrders() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `order`");
        ResultSet set = statement.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (set.next()) {
            orders.add(new Order(
                    set.getString("orderID"),
                    set.getString("branchID"),
                    set.getString("orderDate")
            ));
        }
        return orders;
    }

    public static Order getOrderDetails(String newValue) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `order` WHERE orderID LIKE '%" + newValue + "%'");
        ResultSet set = statement.executeQuery();

        while (set.next()){
            return (new Order(
                    set.getString("orderID"),
                    set.getString("orderDate"),
                    set.getString("branchID")
            ));
        }
        return null;
    }

    public static boolean cancelOrder(String selectedItem) {
        try{
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `orderID` WHERE `orderID` =?");
            statement.setObject(1,selectedItem);
            if (statement.executeUpdate()>0){
                return orderDispatchController.removeSavedOrderEntry(selectedItem);
            }
        }catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean saveOrder(Order order) {
        try {
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `order` VALUES (?,?,?)");
            statement.setObject(1, order.getOrderID());
            statement.setObject(2, order.getBranchID());
            statement.setObject(3, order.getDate());
            if (statement.executeUpdate() > 0) {
                System.out.println("order saved");
                //call method for save items details
                return saveOrderDetails(order.getOrderID(), order.getItems());
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean saveOrderID(String orderID) {
        try {
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `orderID` VALUE (?)");
            statement.setObject(1, orderID);
            if (statement.executeUpdate()>0){
                System.out.println("orderID saved to table");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveOrderDetails(String orderID, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails tempDetails : items) {
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO orderDetails VALUES (?,?,?,?)");
            statement.setObject(2, tempDetails.getItemID());
            statement.setObject(1, orderID);
            statement.setObject(3, tempDetails.getItemDescription());
            statement.setObject(4, tempDetails.getItemQNT());
            if (statement.executeUpdate() > 0) {
                if (updateQNT(tempDetails.getItemID(), tempDetails.getItemQNT())) {

                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQNT(String itemID, int itemQNT) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE item SET itemQNT =(itemQNT - " + itemQNT + ") WHERE itemID = '" + itemID + "'");
        return statement.executeUpdate() > 0;
    }
    public static ArrayList<orderDetailsTM> getOrderListDetails(String newValue) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `orderDetails` WHERE orderID LIKE '%" + newValue + "%'");
        ResultSet set = statement.executeQuery();
        ArrayList<orderDetailsTM> tmArrayList = new ArrayList<>();

        while (set.next()){
            tmArrayList.add (new orderDetailsTM(
                    set.getString("itemID"),
                    set.getString("itemDescription"),
                    set.getInt("itemQNT")
            ));
        }
        return tmArrayList;
    }
}
