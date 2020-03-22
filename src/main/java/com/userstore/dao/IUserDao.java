package com.userstore.dao;

import com.userstore.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {

    public List<User> getAllUsers() throws SQLException;

    public void add(User user) throws SQLException;

    public void edit(User user) throws SQLException;

    public void delete(int id) throws SQLException;

}
