package logics;

import DBUtil.DbConnection;
import model.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bankController {
    public static List<Bank> getAllBanks() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM branch");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Bank> banks = new ArrayList<>();
        while (resultSet.next()) {
            banks.add(new Bank(
                    resultSet.getString("branchID"),
                    resultSet.getString("branchName"),
                    resultSet.getString("branchAddress"),
                    resultSet.getString("branchContact"),
                    resultSet.getString("branchEmail")
            ));
        }
        return banks;
    }

    public static boolean updateBank(Bank bank) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE branch SET branchName=?,branchAddress=?,branchContact=?,branchEmail=? WHERE branchID=? ");
        System.out.println("prepared stm");
        System.out.println(bank.getBranchID());
        preparedStatement.setObject(1, bank.getBranchName());
        preparedStatement.setObject(2, bank.getBranchAddress());
        preparedStatement.setObject(3, bank.getBranchContact());
        preparedStatement.setObject(4, bank.getBranchEmail());
        preparedStatement.setObject(5, bank.getBranchID());
        return preparedStatement.executeUpdate() > 0;
    }

    public static List<Bank> searchBanks(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM branch WHERE branchID LIKE '%" + id + "%'");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Bank> banks = new ArrayList<>();

        while (resultSet.next()) {
            banks.add(new Bank(
                    resultSet.getString("branchID"),
                    resultSet.getString("branchName"),
                    resultSet.getString("branchAddress"),
                    resultSet.getString("branchContact"),
                    resultSet.getString("branchEmail")
            ));
        }
        return banks;
    }

    public static Bank getBankDetailsFromName(String newValue) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM branch WHERE branchName LIKE '%" + newValue + "%'");
        ResultSet rst = stm.executeQuery();



        while (rst.next()) {
            return (new Bank(
                    rst.getString("branchID"),
                    rst.getString("branchName"),
                    rst.getString("branchAddress"),
                    rst.getString("branchContact"),
                    rst.getString("branchEmail")
            ));
        }
        return null;

    }

    public static Bank getBankNameFromID(String newValue) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM branch WHERE branchID LIKE '%" + newValue + "%'");
        ResultSet rst = stm.executeQuery();

        System.out.println(newValue);
        System.out.println("bank Controller");
        while (rst.next()) {
            return (new Bank(
                    rst.getString("branchID"),
                    rst.getString("branchName"),
                    rst.getString("branchAddress"),
                    rst.getString("branchContact"),
                    rst.getString("branchEmail")
            ));
        }
        return null;

    }

    public boolean saveBranch(Bank b1) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO branch VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, b1.getBranchID());
        statement.setObject(2, b1.getBranchName());
        statement.setObject(3, b1.getBranchAddress());
        statement.setObject(4, b1.getBranchContact());
        statement.setObject(5, b1.getBranchEmail());
        return statement.executeUpdate() > 0;
    }
}
