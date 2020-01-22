package com.tsv.servlet;

import com.tsv.model.User;
import com.tsv.util.Utils;

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

        User user = Utils.createStubUser(1, "Sergii Tychkin",38);
        this.users.put(user.getId(), user);
        user = Utils.createStubUser(2, "Viktoriya Tychkina", 34);
        this.users.put(user.getId(), user);
        user = Utils.createStubUser(3, "Violetta Tychkina", 10);
        this.users.put(user.getId(), user);
        System.out.println("contextInitialized : users.size = " + this.users.size());
        servletContext.setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //Close resources
        users=null;

    }
}
