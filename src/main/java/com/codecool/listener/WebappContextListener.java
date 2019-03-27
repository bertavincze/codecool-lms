package com.codecool.listener;

import com.codecool.dao.database.PageList;
import com.codecool.dao.database.UserList;

import com.codecool.model.user.User;
import com.codecool.service.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public final class WebappContextListener implements ServletContextListener {
    private PageList pageList = PageList.getInstance();
    private UserList userList = UserList.getInstance();
    private String homeDir = System.getenv("CATALINA_HOME");
    private String directory = homeDir + "/webapps/";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        registerCharacterEncodingFilter(sce);
        DataSource dataSource = putDataSourceToServletContext(sce);
        runDatabaseInitScript(dataSource, "/goatcool.sql");

        System.out.println("This method is invoked once when the webapp gets deployed.");
        try {
            pageList.loadPageList(directory + "pagelist.ser");
            userList.loadUserList(directory + "userlist.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void registerCharacterEncodingFilter(ServletContextEvent sce) {
        sce.getServletContext().addFilter("SetCharacterEncodingFilter", "org.apache.catalina.filters.SetCharacterEncodingFilter");
    }

    private DataSource putDataSourceToServletContext(ServletContextEvent sce) {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envCtx.lookup("jdbc/codecoolLMS");
            ServletContext servletCtx = sce.getServletContext();
            servletCtx.setAttribute("dataSource", dataSource);
            return dataSource;
        } catch (NamingException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    private void runDatabaseInitScript(DataSource dataSource, String resource) {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource(resource));
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IllegalStateException(t);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");

        try {
            pageList.savePageList(pageList.getPageList(), directory + "pagelist.ser");
            userList.saveUserList(userList.getUsers(), directory + "userlist.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
