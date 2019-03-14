package com.codecool.listener;

import com.codecool.database.PageList;
import com.codecool.database.UserList;
import com.codecool.model.user.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    private PageList pageList = PageList.getInstance();
    private UserList userList = UserList.getInstance();
    private String homeDir = System.getenv("CATALINA_HOME");
    private String directory = homeDir + "/webapps/";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets deployed.");
        try {
            pageList.loadPageList(directory + "pagelist.ser");
            userList.loadUserList(directory + "userlist.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
