package com.userstore.dao;

import com.userstore.dao.jdbc.PostgresqlDatasource;
import com.userstore.dao.jdbc.mapper.UserRowMapper;
import com.userstore.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private DataSource dataSource;

    private final String GET_ALL = "select * from users;";
    private final String INSERT_USER = "insert into users(id, first_name, last_name, salary) values (?,?,?,?);";
    private final String UPDATE_USER = "update users set first_name = ?, last_name = ?, salary = ? where id = ?;";
    private final String DELETE_USER = "delete from users where id = ?;";

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(GET_ALL);) {
            UserRowMapper rowMapper = new UserRowMapper();
            while (resultSet.next()) {
                User user = rowMapper.mapRow(resultSet);
                users.add(user);
            }
        }
        return users;
    }

    public void add(User user) throws SQLException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER);) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setInt(4, user.getSalary());

            statement.execute();
        }
    }

    public void edit(User user) throws SQLException {

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
        ) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, (int) user.getSalary());
            statement.setInt(4, user.getId());

            statement.execute();
        }
    }

    public void delete(int id) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_USER);
        ) {
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

}
