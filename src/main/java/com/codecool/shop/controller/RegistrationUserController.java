package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/registration")
public class RegistrationUserController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.info("DoGet sign page!");
        try {

            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("user/registration.html", context, resp.getWriter());
            LOG.info("Entered sign page!");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Could not load sign page!");
        }

//        String email = req.getParameter("form-username");
//        String password = req.getParameter("form-password");
//
//        System.out.println("sa mor de nu te bat daca mai dai null inca odata");
//        System.out.println(email);
//        System.out.println(password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("DoPost sign page!");
        System.out.println("POST METHOD");


    }
}
