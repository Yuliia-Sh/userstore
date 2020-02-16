package com.study.lab1.service;

import com.study.lab1.dao.UserDao;
import com.study.lab1.model.User;
import com.study.lab1.templater.PageGenerator;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            throw new Exception(errorMessage);
        }
    }

    public void editUser(User user) throws Exception {
        String errorMessage = checkUserData(user);
        if (errorMessage.isEmpty()) {
            userDao.edit(user);
        } else {
            throw new Exception(errorMessage);
        }
    }

    public void deleteUser(int id) throws SQLException {
        userDao.delete(id);
    }

    public User getUserFromRequest (HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Double salary=Double.parseDouble(request.getParameter("salary"));

        return new User(id, firstName, lastName, salary);
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
