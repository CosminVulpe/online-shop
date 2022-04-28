package com.codecool.shop.controller;

import com.codecool.shop.dao.db.ShopDbManager;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/loginValidation"})
public class LoginValidationController extends HttpServlet {
    private ShopDbManager sp = new ShopDbManager();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sp.run();
        String password = req.getParameter("password");
        String userName = req.getParameter("username");

        List<User> allUsers = sp.getAllUsers();
        for (User user : allUsers) {
            if (userName.equals(user.getName()) || password.equals(user.getPassword()) ){
                HttpSession session = req.getSession();
                session.setAttribute("username", userName);
                resp.sendRedirect("/");
                return;
            }
        }
        resp.sendRedirect("/login");
    }
}