package logics;

import DBUtil.DbConnection;
import javafx.scene.control.Alert;
import model.Supplier;
import model.supplierPayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class supplierController {
    public static List<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()){
            suppliers.add(new Supplier(
                    resultSet.getString("supplierID"),
                    resultSet.getString("supplierName"),
                    resultSet.getString("supplierAddress"),
                    resultSet.getString("supplierContact"),
                    resultSet.getDouble("amountToBePayed")
            ));
        }
        return suppliers;
    }



    public static boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM supplier WHERE supplierID = ?");
        try {
            preparedStatement.setObject(1,id);
            preparedStatement.executeUpdate();
            new Alert(Alert.AlertType.CONFIRMATION,"new Success.!").show();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return preparedStatement.executeUpdate()>0;
    }


    public boolean saveSupplier(Supplier supplier1) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO supplier VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setObject(1,supplier1.getSupplierID());
        preparedStatement.setObject(2,supplier1.getSupplierName());
        preparedStatement.setObject(3,supplier1.getSupplierAddress());
        preparedStatement.setObject(4,supplier1.getSupplierContact());
        preparedStatement.setObject(5,supplier1.getAmountToBePayed());
        return  preparedStatement.executeUpdate()>0;
    }

    public List<String> getAllIDs() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT supplierID FROM Supplier");
        ResultSet rst = statement.executeQuery();

        ArrayList<String> allIDs = new ArrayList<>();
        while (rst.next()){
            allIDs.add(
                    rst.getString(1)
            );

        }
        return allIDs;
    }

    public boolean updateAmountToPay(String supplierID, Double tempAmount) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE supplier SET amountToBePayed =(amountToBePayed + " + tempAmount + ") WHERE supplierID = '" + supplierID + "'");
        return statement.executeUpdate() > 0;
    }
}
