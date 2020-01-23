package com.tsv.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsv.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final User user = new User(1, "Java Vision", 1);
        final String json = new ObjectMapper().writeValueAsString(user);
        resp.getWriter().write(json);
    }
}