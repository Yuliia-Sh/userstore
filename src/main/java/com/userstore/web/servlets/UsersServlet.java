package com.userstore.web.servlets;


import com.userstore.entity.User;
import com.userstore.service.UserService;
import com.userstore.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    UserService userService;

    public UsersServlet(UserService userService) {
        this.userService = userService;
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        try {
            List<User> users = userService.getAllUsers();

            Map<String, Object> root = new HashMap<>();
            root.put("users", users);

            response.getWriter().println(PageGenerator.instance().getPage("users.html", root));
        } catch (SQLException e) {
            e.printStackTrace();

            Map<String, Object> pageVariables = new HashMap<>();

            pageVariables.put("message", e.getMessage());
            response.getWriter().println(PageGenerator.instance().getPage("message.html", pageVariables));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

}
