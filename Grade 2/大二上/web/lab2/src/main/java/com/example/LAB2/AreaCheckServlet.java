package com.example.LAB2;

import com.example.LAB2.Results.ListOfResult;
import com.example.LAB2.Results.Result;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String X = req.getParameter("X");
        String R = req.getParameter("R");
        String Y = req.getParameter("Y");
        double y = Double.parseDouble(Y);
        double r = Double.parseDouble(R);
        double x = Double.parseDouble(X);
        boolean in_range = checkRange(x,y,r);
        HttpSession session = req.getSession();
        ListOfResult list =(ListOfResult) session.getAttribute("list");
        if(list==null){
            list = new ListOfResult();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        double drawY = (150-100*y/r);
        double drawX = (175+x*100/r);
        df.format(drawX);
        df.format(drawY);
        df.format(x);
        df.format(y);
        list.addResult(new Result(drawX,drawY,r,in_range,x,y));
        session.setAttribute("list",list);
        session.setAttribute("result",new Result(drawX,drawY,r,in_range,x,y));
        setResult(x,y,r,in_range,session);
        resp.sendRedirect("result.jsp");
    }

    protected boolean checkRange(double x, double y, double r) {
        if(x<=0 && x > -r){
            if((y < r) && (x*x+y*y < r*r)){
                return true;
            }
            return false;
        }else if(x >=0 && x < r){
            if(y >= 0 && y < -x+r){
                return true;
            }
        }
        return false;
    }

    private void setResult(double x, double y, double r, boolean result,HttpSession session){
        session.setAttribute("x", x);
        session.setAttribute("y", y);
        session.setAttribute("r", r);
        session.setAttribute("xGraph", (175+x*100/r));
        session.setAttribute("yGraph", (150-100*y/r));
        session.setAttribute("inRange", result ? "yes" : "no");
    }
}
