/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Feature;
import model.Group;

/**
 *
 * @author Administrator
 */
public abstract class BaseRequire extends HttpServlet {
     private boolean checkAuthentication(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            return false;
        }

        String url = request.getServletPath();
        for (Group group : account.getListGroup()) {
            for (Feature feature : group.getListFeature()) {
                if (feature.getUrl().equals(url)) {
                    return true;
                }
            }
        }
        return false;

    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(checkAuthentication(request)){
            processGet(request,response);
            
        }else
        {
            response.getWriter().println("Access Denie");
        }
        
    }
     protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException; 
    
     protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException; 
    



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          if(checkAuthentication(request)){
            processPost(request,response);
            
        }else
        {
            response.getWriter().println("Access Denie");
        }
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
