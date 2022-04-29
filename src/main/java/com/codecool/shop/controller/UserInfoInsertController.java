package com.codecool.shop.controller;

import com.codecool.shop.dao.db.ShopDbManager;
import com.codecool.shop.model.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/insert"})
public class UserInfoInsertController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShopDbManager sp = new ShopDbManager();
        sp.run();

        HttpSession session = req.getSession();
        int zipcode = Integer.parseInt(req.getParameter("zipcode_bill"));
        int zipcodeShip = Integer.parseInt(req.getParameter("zipcode_ship"));
        int userId = (int) req.getSession().getAttribute("id");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phone_number");
        String city = req.getParameter("city_bill");
        String address = req.getParameter("address_bill");
        String cityShip = req.getParameter("city_ship");
        String addressShip = req.getParameter("address_ship");
        String country = req.getParameter("countries");
        String country2 = req.getParameter("countries2");
        sp.addUserInfo(new UserInfo("alex",
                zipcode,
                zipcodeShip,
                userId,
                firstName,
                lastName,
                email,
                phoneNumber,
                country,
                city,
                address,
                country2,
                cityShip,
                addressShip));
        System.out.println(sp.getUserInfo(userId));
        resp.sendRedirect("/userInfo");
    }
}
