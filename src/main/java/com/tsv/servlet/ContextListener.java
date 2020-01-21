package com.tsv.servlet;

import com.tsv.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener {
    private Map<Integer, User> users;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        users = new ConcurrentHashMap<>();
        servletContext.setAttribute("users", users);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //Close resources
        users=null;

    }
}
