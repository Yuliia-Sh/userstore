package com.study.lab1.servlets;


import com.study.lab1.service.UserService;
import com.study.lab1.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = new HashMap<>();

        UserService userService = UserService.getInstance();
        try {
            userService.deleteUser(id);
            pageVariables.put("message", " User with id=" + Integer.toString(id) + " is deleted");
            response.getWriter().println(PageGenerator.instance().getPage("message_success.html", pageVariables));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            pageVariables.put("message", e.getMessage());
            response.getWriter().println(PageGenerator.instance().getPage("message.html", pageVariables));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        };

    }

}