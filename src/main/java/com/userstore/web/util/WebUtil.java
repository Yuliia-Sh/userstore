package com.userstore.web.util;


import com.userstore.entity.User;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    public static User getUserFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setSalary(Integer.parseInt(request.getParameter("salary")));

        return user;
    }
}
