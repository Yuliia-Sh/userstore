package com.study.lab1.datasource;

import com.study.lab1.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlDatasource{
    private Connection connection;

    public boolean isConnect() throws SQLException {
        return (connection!=null && !connection.isClosed());
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "postgres");
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "Select * from users;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int salary=resultSet.getInt("salary");
            User user= new User(id, firstName, lastName, salary);
            users.add(user);
        }
        return users;
    }

    public void addUser(User user) throws SQLException {
        String sql = "insert into users(id, first_name, last_name, salary) values (?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        statement.setString(2, user.getFirstName());
        statement.setString(3,user.getLastName());
        statement.setInt(4,(int)user.getSalary());

        statement.execute();
    }

    public void updateUser(User user) throws SQLException {
        String sql = "update users set first_name = ?, last_name = ?, salary = ? where id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getFirstName());
        statement.setString(2,user.getLastName());
        statement.setInt(3, (int)user.getSalary());
        statement.setInt(4, user.getId());

        statement.execute();
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "delete from users where id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        statement.executeUpdate();
    }
}
