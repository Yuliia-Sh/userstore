package com.study.lab1.servlets;


import com.study.lab1.model.User;
import com.study.lab1.service.UserService;
import com.study.lab1.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EditUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserService.getInstance();
        User user = userService.getUserFromRequest(request);

        Map<String, Object> root = new HashMap<>();
        root.put("user", user);

        response.getWriter().println(PageGenerator.instance().getPage("edit_user.html", root));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserService.getInstance();
        User user = userService.getUserFromRequest(request);


        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = new HashMap<>();

        try {
            userService.editUser(user);
            pageVariables.put("message", user.toString() + " is updated");
            response.getWriter().println(PageGenerator.instance().getPage("message_success.html", pageVariables));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            pageVariables.put("message", e.getMessage());
            response.getWriter().println(PageGenerator.instance().getPage("message.html", pageVariables));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
