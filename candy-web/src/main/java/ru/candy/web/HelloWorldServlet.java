package ru.candy.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name")!=null) {
            resp.getWriter().print(req.getParameter("name")+", hello!");
        }else{
            resp.getWriter().print("Hello beautiful world!");
        }
    }
}
