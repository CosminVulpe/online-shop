package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.db.ShopDbManager;
import com.codecool.shop.model.UserInfo;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/userInfo"})
public class UserInfoController extends HttpServlet {
    ShopDbManager sp = new ShopDbManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ( req.getSession().getAttribute("id") == null) resp.sendRedirect("/");
        sp.run();
        UserInfo userInfo = sp.getUserInfo((int) req.getSession().getAttribute("id"));
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        if (userInfo != null){
            context.setVariable("first_name", userInfo.getFirstName());
            context.setVariable("last_name", userInfo.getLastName());
            context.setVariable("email", userInfo.getEmail());
            context.setVariable("phone_number", userInfo.getPhoneNumber());
            context.setVariable("country", userInfo.getCountry());
            context.setVariable("city", userInfo.getCity());
            context.setVariable("address", userInfo.getAddress());
            context.setVariable("zipcode", userInfo.getZipcode());
            context.setVariable("country2", userInfo.getCountryShip());
            context.setVariable("city2", userInfo.getCityShip());
            context.setVariable("address2", userInfo.getAddressShip());
            context.setVariable("zipcode2", userInfo.getZipcodeShip());
        }
        engine.process("user/user_info.html", context, resp.getWriter());

    }
}