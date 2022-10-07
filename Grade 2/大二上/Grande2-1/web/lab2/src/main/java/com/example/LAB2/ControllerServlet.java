package com.example.LAB2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ControllerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String R = req.getParameter("R");
        String X = req.getParameter("X");
        String Y = req.getParameter("Y");
        boolean allPara = check(X,R,Y);
        try {
            if (allPara) {
                double r = Double.parseDouble(R);
                double y = Double.parseDouble(Y);
                double x = Double.parseDouble(X);
                boolean overRange = checkValues(x, y, r,req);
                if (overRange) {
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                } else {
                    req.setAttribute("Mistake",null);
                    req.getRequestDispatcher("/Checker").forward(req, resp);
                }
            } else {
                req.setAttribute("Mistake","Make sure you input all values");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }catch (NumberFormatException n){
            req.setAttribute("Mistake","Make sure you entry an accessible number for y");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    protected boolean checkValues(double x, double y, double r, HttpServletRequest req){
        if(y<-3.0D || y>3.0D){
            req.setAttribute("Mistake","Your Y is out of range. Make sure it is set in [-3,3]");
            return true;
        }
        if(x<-4.0D || x>4.0D){
            req.setAttribute("Mistake","Your X is out of range. Make sure it is set in [-4,4]");
            return true;
        }
        if(r<1.0D || r> 3.0D){
            req.setAttribute("Mistake","Your R is out of range. Make sure it is set in [1,3]");
            return true;
        }
        return false;
    }

    protected boolean check(String a, String b, String c) {
        if(a==null||b==null||c==null){
            return false;
        }else if(a.equals("")||b.equals("")||c.equals("")){
            return false;
        }else {
            return true;
        }
    }

}