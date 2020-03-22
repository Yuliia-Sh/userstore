package com.userstore.web.servlets;


import com.userstore.service.UserService;
import com.userstore.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteUserServlet extends HttpServlet {

    UserService userService;

    public DeleteUserServlet(UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> pageVariables = new HashMap<>();

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
        }

    }

}
