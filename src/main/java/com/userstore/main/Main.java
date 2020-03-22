package com.userstore.main;

import com.userstore.dao.UserDao;
import com.userstore.dao.jdbc.PostgresqlDatasource;
import com.userstore.service.UserService;
import com.userstore.util.PropertyReader;
import com.userstore.web.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) throws Exception {

        PropertyReader propertyReader = new PropertyReader("userStoreConfig.ini");

        DataSource dataSource = new PostgresqlDatasource(propertyReader.getProperties());
        UserDao userDao = new UserDao(dataSource);
        UserService userService = new UserService(userDao);
        UsersServlet usersServlet = new UsersServlet(userService);
        AddUserServlet addUserServlet = new AddUserServlet(userService);
        EditUserServlet editUserServlet = new EditUserServlet(userService);
        DeleteUserServlet deleteUserServlet = new DeleteUserServlet(userService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(usersServlet), "/users");
        context.addServlet(new ServletHolder(addUserServlet), "/user/add");
        context.addServlet(new ServletHolder(editUserServlet), "/user/edit");
        context.addServlet(new ServletHolder(deleteUserServlet), "/user/remove");

        Server server = new Server(Integer.parseInt(propertyReader.getProperty("web.port")));
        server.setHandler(context);

        server.start();
    }
}

