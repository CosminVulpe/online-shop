package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.db.ShopDbManager;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.UserInfo;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(urlPatterns = {"checkout"})
public class CheckoutCartController extends HttpServlet {
    private ShopDbManager sp = new ShopDbManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sp.run();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        UserInfo userInfo = sp.getUserInfo((int) req.getSession().getAttribute("id"));
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
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
        engine.process("product/checkout/checkout-cart.html", context, resp.getWriter());
    }
}
