package logics;

import DBUtil.DbConnection;
import model.DeliveryTeam;
import sun.dc.pr.PRError;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class deliveryTeamController {
    public static List<DeliveryTeam> getAllTeams() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM deliveryTeam");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<DeliveryTeam> teams = new ArrayList<>();
        while (resultSet.next()) {
            teams.add(new DeliveryTeam(
                    resultSet.getString("teamID"),
                    resultSet.getString("teamName"),
                    resultSet.getString("teamContact"),
                    resultSet.getString("teamAddress")
            ));
        }
        return teams;
    }

    public static boolean updateTeam(DeliveryTeam deliveryTeam) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE deliveryTeam SET teamName = ?,teamContact = ?,teamAddress = ? WHERE teamID = ?");
        preparedStatement.setObject(1,deliveryTeam.getTeamName());
        preparedStatement.setObject(2,deliveryTeam.getTeamContact());
        preparedStatement.setObject(3,deliveryTeam.getTeamAddress());
        preparedStatement.setObject(4,deliveryTeam.getTeamID());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean saveTeam(DeliveryTeam deliveryTeam) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO deliveryTeam VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, deliveryTeam.getTeamID());
        preparedStatement.setObject(2, deliveryTeam.getTeamName());
        preparedStatement.setObject(3, deliveryTeam.getTeamContact());
        preparedStatement.setObject(4, deliveryTeam.getTeamAddress());
        return preparedStatement.executeUpdate() > 0;
    }

}
