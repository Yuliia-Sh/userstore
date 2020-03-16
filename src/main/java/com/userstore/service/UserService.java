package com.userstore.service;

import com.userstore.dao.UserDao;
import com.userstore.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    private static UserService instance;

    private UserService() { }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> getAllUsers() throws SQLException {
       List<User> users = userDao.getAllUsers();
       return users;
    }

    public void addUser(User user) throws Exception {
        String errorMessage = checkUserData(user);
        if (errorMessage.isEmpty()) {
            userDao.add(user);
        } else {
            throw new RuntimeException(errorMessage);
        }
    }

    public void editUser(User user) throws Exception {
        String errorMessage = checkUserData(user);
        if (errorMessage.isEmpty()) {
            userDao.edit(user);
        } else {
            throw new RuntimeException(errorMessage);
        }
    }

    public void deleteUser(int id) throws SQLException {
        userDao.delete(id);
    }

    private String checkUserData(User user) {
        String errorMessage="";
        if (user.getFirstName()==null || user.getFirstName().isEmpty()) {
            errorMessage = "First Name should be filled";
        } else if (user.getLastName()==null || user.getLastName().isEmpty()) {
            errorMessage = "Last Name should be filled";
        } else if (user.getSalary()<0) {
            errorMessage = "Salary should be positive value";
        }
        return errorMessage;
    }
}
