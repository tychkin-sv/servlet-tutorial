package com.tsv.servlet;

import com.tsv.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetIndexPageServlet extends HttpServlet {
    private static String index = "/WEB-INF/view/index.jsp";

    private List<User> users;

    @Override
    public void init() throws ServletException {
        System.out.println("*****SERVLET IS INIT*****");
        System.out.println("FOR PATH '/' WILL RENDER VIEW : "+index);

        users = new CopyOnWriteArrayList<>();
        users.add(new User("Sergii Tychkin", 38));
        users.add(new User("Viktoriya Tychkina", 34));
        users.add(new User("Violetta Tychkina", 10));

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);  // Не надо переопределять
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet is work!");
        req.setAttribute("users", users);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        if (!requestIsValid(req)){
            doGet(req,resp);
        }

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        final User user = new User(name, Integer.valueOf(age));
        users.add(user);
        doGet(req, resp);
    }


    @Override
    public void destroy() {
        System.out.println("*****SERVLET IS DESTROY*****");
    }

    private boolean requestIsValid(final HttpServletRequest req) {

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        return name != null && name.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }
}
