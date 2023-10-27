package com.dinerinfo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 終止Session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        
        
        // 重定向到登入頁面或首頁
        res.sendRedirect(req.getContextPath() + "/dinerbackground/pages/Team/login/login-form.jsp");
    }
}
