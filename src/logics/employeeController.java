package logics;

import DBUtil.DbConnection;
import model.Bank;
import model.Employee;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeeController {
    public static List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()){
            employees.add(new Employee(
                    resultSet.getString("employeeNIC"),
                    resultSet.getString("employeeName"),
                    resultSet.getString("employeeAddress"),
                    resultSet.getString("employeeContact"),
                    resultSet.getString("employeeJobType")

            ));
        }
        return employees;

    }

    public static boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Connection connection  = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET employeeName = ?,employeeAddress = ?, employeeContact=?,employeeJobType = ? WHERE employeeNIC = ?");
        preparedStatement.setObject(1,employee.getEmployeeName());
        preparedStatement.setObject(2,employee.getEmployeeAddress());
        preparedStatement.setObject(3,employee.getEmployeeContact());
        preparedStatement.setObject(4,employee.getEmployeeJobType());
        preparedStatement.setObject(5,employee.getEmployeeNIC());
        return preparedStatement.executeUpdate()>0;
    }


    public boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO employee VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,employee.getEmployeeNIC());
        preparedStatement.setObject(2,employee.getEmployeeName());
        preparedStatement.setObject(3,employee.getEmployeeAddress());
        preparedStatement.setObject(4,employee.getEmployeeContact());
        preparedStatement.setObject(5,employee.getEmployeeJobType());

        return preparedStatement.executeUpdate()>0;
    }
    public static List<Employee> searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE employeeNIC LIKE '%"+id+"%'");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Employee> employees = new ArrayList<>();

        while(resultSet.next()){
           employees.add(new Employee(
                    resultSet.getString("employeeNIC"),
                    resultSet.getString("employeeName"),
                    resultSet.getString("employeeAddress"),
                    resultSet.getString("employeeContact"),
                    resultSet.getString("employeeJobType")
            ));
        }
        return employees;
    }
}
