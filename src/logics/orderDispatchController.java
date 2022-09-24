package logics;

import DBUtil.DbConnection;
import model.DispatchOrder;
import model.ItemDetails;
import view.tm.orderDetailsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderDispatchController {

    public static List<DispatchOrder> getAllDispatchedOrders() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `dispatchedOrders`");
        ResultSet set = statement.executeQuery();

        ArrayList<DispatchOrder> orderList = new ArrayList<>();
        while (set.next()){
            System.out.println(set.getString(1));
            orderList.add(new DispatchOrder(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)

            ));
        }
        System.out.println(orderList.toString());
        return orderList;
    }

    public static DispatchOrder getAllDispatchedOrderDetails(String newValue) throws SQLException, ClassNotFoundException {
    PreparedStatement statement =DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `dispatchedOrders` WHERE orderID LIKE '%" + newValue + "%'");
    ResultSet set =statement.executeQuery();
    while (set.next()){
        return (new DispatchOrder(
                set.getString(1),
                set.getString(5),
                set.getString(4),
                set.getString(2),
                set.getString(3)
        ));
    }
    return null;
    }

    public static ArrayList<orderDetailsTM> getAllOrderListDetails(String newValue) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `dispatchedOrderDetails` WHERE orderID LIKE '%" + newValue + "%'");
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

    public boolean dispatchOrder(DispatchOrder dispatchOrder){
        try {
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `dispatchedOrders` VALUES (?,?,?,?,?)");
            System.out.println("1");
            System.out.println(dispatchOrder.toString());
            statement.setObject(1, dispatchOrder.getOrderID());
            statement.setObject(2, dispatchOrder.getbranchID());
            statement.setObject(3, dispatchOrder.getDeliveryTeamName());
            statement.setObject(4, dispatchOrder.getOrderData());
            statement.setObject(5, dispatchOrder.getDispatchedDate());
            if (statement.executeUpdate() > 0) {
                System.out.println("dispatchedOrder saved");
                //call method for save items details
                return saveDispatchedOrdersDetails(dispatchOrder.getOrderID(), dispatchOrder.getItems());

            } else {
                return false;
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return true;
    }

    private boolean saveDispatchedOrdersDetails(String orderID, ArrayList<orderDetailsTM> items) {
            try {
                for (orderDetailsTM itemDetails:items) {
                    PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `dispatchedOrderDetails` VALUES (?,?,?,?)");
                    statement.setObject(1,orderID);
                    statement.setObject(2,itemDetails.getItemID());
                    statement.setObject(3,itemDetails.getItemDescription());
                    statement.setObject(4,itemDetails.getItemQNT());
                    if (statement.executeUpdate()>0){
                        System.out.println("not called remove method");
                      /*if(removeSavedOrderEntry(orderID)){
                           System.out.println("done");
                           return true;
                       }else{
                           return false;*/
                       } else{
                        return false;
                    }
                }
            } catch (SQLException |ClassNotFoundException e) {
                e.printStackTrace();
            }
        return true;
        }

    public static boolean removeSavedOrderEntry(String orderID) {
        try {
            //remove orerDetails
            PreparedStatement statement1 = DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM `orderDetails` WHERE orderID = ?");
            statement1.setObject(1,orderID);

            //remove order
            PreparedStatement statement2 = DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM `order` WHERE orderID = ?");
            statement2.setObject(1,orderID);
            System.out.println("Data remove method");
            if (statement1.executeUpdate()>0 && statement2.executeUpdate()>0){
                return true;
            }

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;

    }

}
