package com.userstore.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.userstore.entity.User;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setSalary(resultSet.getInt("salary"));

        return user;
    }
}
