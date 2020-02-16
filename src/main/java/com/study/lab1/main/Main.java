package com.study.lab1.main;

import com.study.lab1.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        UsersServlet usersServlet= new UsersServlet();
        AddUserServlet addUserServlet = new AddUserServlet();
        EditUserServlet editUserServlet = new EditUserServlet();
        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(usersServlet), "/users");
        context.addServlet(new ServletHolder(addUserServlet), "/user/add");
        context.addServlet(new ServletHolder(editUserServlet), "/user/edit");
        context.addServlet(new ServletHolder(deleteUserServlet), "/user/remove");

        Server server = new Server(8084);
        server.setHandler(context);

        server.start();
    }
}

