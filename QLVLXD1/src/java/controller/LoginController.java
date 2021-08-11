/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BaseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Administrator
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String username=null;
        String password = null;
        
        if(cookies != null){
        for(Cookie c : cookies){
            if(c.getName().equalsIgnoreCase("rememberUsername")){
                 username = c.getValue();
                 
            }
            
            if(c.getName().equalsIgnoreCase("rememberPassword")){
                password = c.getValue();
            }
        }
        }
        
        request.setAttribute("rememberUsername", username);
        request.setAttribute("rememberPassword", password);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");
        BaseDAO dD = new BaseDAO();
        Account a = dD.getByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        
        
        if(a != null){
            Cookie cookie = new Cookie("rememberUsername",a.getUsername());
            cookie.setMaxAge(3600);
            Cookie cookie1 = new Cookie("rememberPassword",a.getPassword());
                 cookie1.setMaxAge(3600);
            response.addCookie(cookie);
            response.addCookie(cookie1);
            
            request.getSession().setAttribute("account", a);
            response.sendRedirect("Order");
        }else
        {
            response.getWriter().println("Login Faild");
        }
       
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
