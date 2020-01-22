package com.tsv.servlet;

import com.tsv.model.User;
import com.tsv.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AddUserServlet extends HttpServlet {
    private Map<Integer, User> users;
    private AtomicInteger id;


    @Override
    public void init() throws ServletException {

        final Object users = getServletContext().getAttribute("users");
        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("You're repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }
        id = new AtomicInteger(this.users.size()+1);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        if (Utils.requestIsValid(req)) {
            final String name = req.getParameter("name");
            final String age = req.getParameter("age");
            final User user = new User();
            final int id = this.id.getAndIncrement();
            user.setId(id);
            user.setAge(Integer.valueOf(age));
            user.setName(name);
            users.put(id, user);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String id = req.getParameter("id");

        if ( !Utils.idIsValid(id, users) ) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        final User user = users.get(Integer.parseInt(id));
        req.setAttribute("user", user);

        req.getRequestDispatcher("/WEB-INF/view/update.jsp")
                .forward(req, resp);
    }


}
