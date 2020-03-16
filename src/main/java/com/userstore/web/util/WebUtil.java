package com.userstore.web.util;


import com.userstore.entity.User;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    public static User getUserFromRequest (HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        int salary= Integer.parseInt(request.getParameter("salary"));

        return new User(id, firstName, lastName, salary);
    }
}
