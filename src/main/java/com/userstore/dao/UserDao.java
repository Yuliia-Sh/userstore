package com.userstore.dao;

import com.userstore.dao.jdbc.PostgresqlDatasource;
import com.userstore.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private PostgresqlDatasource dataSource;

    private void checkConnection() throws SQLException {
        if (dataSource==null) {
            dataSource = new PostgresqlDatasource();
            dataSource.connect();
        }
        if (!dataSource.isConnect()) {
            dataSource.connect();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        checkConnection();
        return dataSource.getAllUsers();
    }

    public void add(User user) throws SQLException {
        checkConnection();
        dataSource.addUser(user);
    }

    public void edit(User user) throws SQLException {
        checkConnection();
        dataSource.updateUser(user);
    }

    public void delete(int id) throws SQLException {
        checkConnection();
        dataSource.deleteUser(id);
    }
}
