package logics;

import DBUtil.DbConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class itemController {
    public static List<Item> getAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement =connection.prepareStatement("SELECT * FROM item");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Item> items = new ArrayList<>();
        while (resultSet.next()){
            items.add(new Item(
                    resultSet.getString("itemID"),
                    resultSet.getString("itemDescription"),
                    resultSet.getInt("itemQNT"),
                    resultSet.getDouble("itemUnitPrice"),
                    resultSet.getString("supplierID")
            ));
        }
        return items;
    }

    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO item VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1,item.getItemID());
        statement.setObject(2,item.getItemDescription());
        statement.setObject(3,item.getItemQNT());
        statement.setObject(4,item.getItemUnitePrice());
        statement.setObject(5,item.getSupplierID());
        return statement.executeUpdate()>0;
    }



    public Item getItemsDetails( String itemName) throws SQLException, ClassNotFoundException {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement("SELECT * FROM item WHERE itemDescription LIKE '%" + itemName + "%'");
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );
        }
            return  null;

    }
}
