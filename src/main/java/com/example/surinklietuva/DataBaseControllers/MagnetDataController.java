package com.example.surinklietuva.DataBaseControllers;

import com.example.surinklietuva.DataStructures.Magnet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagnetDataController {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public List<Magnet> selectMagnet(String query) throws SQLException {
        List<Magnet> listofMagnets = new ArrayList<>();
        connection = DbOperations.connectToDb();
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            String area = rs.getString("area");
            String name = rs.getString("name");
            String shops = rs.getString("shops");

            listofMagnets.add(new Magnet(area,name,shops));
        }

        DbOperations.disconnectFromDb(connection, statement);
        return listofMagnets;
    }


    public void addMagnetToUser(String magnetName, int userID) throws SQLException {
        connection = DbOperations.connectToDb();
        String insertString = "INSERT INTO `user_magnet`(`userID`, `magnetName`) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(insertString);
        preparedStatement.setInt(1, userID);
        preparedStatement.setString(2, magnetName);
        preparedStatement.execute();
        DbOperations.disconnectFromDb(connection, preparedStatement);
    }

    public void removeMagnetToUser(String magnetName, int userID) throws SQLException {
        connection = DbOperations.connectToDb();
        statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM `user_magnet` WHERE `userID` = '"+userID+"' AND `magnetName` = '"+magnetName+"'");
    }
}


