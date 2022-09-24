package logics;

import DBUtil.DbConnection;
import model.supplierPayHistory;
import model.supplierPayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class paymentController {


    public static String setPaymentID() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT paymentID FROM Payment ORDER BY paymentID DESC LIMIT 1").executeQuery();
        if (set.next()) {
            int tempID = Integer.parseInt(set.getString(1).split("-")[1]);
            tempID = tempID + 1;
            if (tempID <= 9) {
                return "P-00" + tempID;
            } else if (tempID <= 99) {
                return "P-0" + tempID;
            } else {
                return "P-" + tempID;
            }

        } else {
            return "P-001";
        }
    }

    public static boolean updateSupplierAmountToPay(String tempSupplierID, Double tempPayAmount, String tempDate) throws SQLException, ClassNotFoundException {
        System.out.println("update method");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("UPDATE supplier SET amountToBePayed = (amountToBePayed - " + tempPayAmount + ") WHERE supplierID = '" + tempSupplierID + "'");
        if (statement.executeUpdate() > 0) {
            System.out.println("1 if");
            String tempPaymentID = setPaymentID();
            supplierPayment payment = new supplierPayment(tempPaymentID, tempSupplierID, tempDate, tempPayAmount);
            if (savePayment(payment)) {
                System.out.println("all process done in generate payment record");
                return true;
            }
        }
        return false;
    }

    public static boolean savePayment(supplierPayment supplierPayment) throws SQLException, ClassNotFoundException {
        System.out.println("save method");
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO payment VALUES(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setObject(1, supplierPayment.getPaymentID());
        stm.setObject(2, supplierPayment.getSupplierID());
        stm.setObject(3, supplierPayment.getDate());
        stm.setObject(4, supplierPayment.getAmount());

        return stm.executeUpdate() > 0;
    }

    public static ArrayList<supplierPayHistory> getAllRecords() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM payment");
        ResultSet set = statement.executeQuery();

        ArrayList<supplierPayHistory> historyArrayList = new ArrayList<>();
        while (set.next()) {
            historyArrayList.add(new supplierPayHistory(
                    set.getString(1),
                    set.getString(2),
                    set.getDate(3),
                    set.getDouble(4)

            ));
        }
        return historyArrayList;

    }
}

