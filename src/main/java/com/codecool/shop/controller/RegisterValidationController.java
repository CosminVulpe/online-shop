package com.codecool.shop.controller;

import com.codecool.shop.dao.db.ShopDbManager;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/registerValidation"})
public class RegisterValidationController extends HttpServlet {
    private ShopDbManager sp = new ShopDbManager();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sp.run();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");
        String userName = req.getParameter("username");

        List<User> allUsers = sp.getAllUsers();
        if (!password.equalsIgnoreCase(confirmPassword)) {
            resp.sendRedirect("/registration");
            return;
        } else {
            for (User user : allUsers) {
                if (userName.equals(user.getName()) || email.equals(user.getEmail())) {
                    resp.sendRedirect("/registration");
                    return;
                }
            }
        }
        sp.addUser(new User(userName, email, password));
        resp.sendRedirect("/login");
    }
}
