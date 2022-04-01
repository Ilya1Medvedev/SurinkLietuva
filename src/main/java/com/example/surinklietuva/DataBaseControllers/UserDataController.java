package com.example.surinklietuva.DataBaseControllers;

import com.example.surinklietuva.DataStructures.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataController {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public List<User> selectUser(String query) throws SQLException {
        List<User> listOfUsers = new ArrayList<>();
        connection = DbOperations.connectToDb();
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            int userID = rs.getInt("userID");
            String  userName = rs.getString("name");
            String  userSurname = rs.getString("surname");
            String  userLogin = rs.getString("username");
            String  userMail = rs.getString("mail");
            String  userPassword = rs.getString("password");
            listOfUsers.add(new User(userID, userName, userSurname, userLogin, userMail, userPassword));
        }
        DbOperations.disconnectFromDb(connection, statement);
        return listOfUsers;
    }

    public void createUser(User user) throws SQLException {
        connection = DbOperations.connectToDb();
        String insertString = "INSERT INTO `user`(`name`, `surname`, `username`, `mail`, `password`) VALUES (?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(insertString);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getMail());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.execute();
        DbOperations.disconnectFromDb(connection, preparedStatement);
    }
}
