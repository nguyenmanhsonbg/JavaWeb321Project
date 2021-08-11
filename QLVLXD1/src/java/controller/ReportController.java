/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CustomerDAO;
import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

/**
 *
 * @author Admin
 */
public class ReportController extends HttpServlet {

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
        CustomerDAO cuD = new CustomerDAO();
        
        OrderDAO oD = new OrderDAO();
        ArrayList<Order> listOrder = oD.getAllOrders(null,null,null);
        
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("listCustomer", cuD.getListCustomer());
        request.getRequestDispatcher("report.jsp").forward(request, response);
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
        CustomerDAO cuD = new CustomerDAO();
        
        String name = request.getParameter("customerName");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        request.setAttribute("name", name);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
       
          OrderDAO od = new OrderDAO();
        ArrayList<Order> lstOrder = od.getAllOrders(name,from,to);
         double total=0;
        for(int i=0;i<lstOrder.size();i++){
            total += lstOrder.get(i).getValue();
        }
         request.setAttribute("listCustomer", cuD.getListCustomer());
        request.setAttribute("total", total);
        request.setAttribute("listOrder", lstOrder);
        request.getRequestDispatcher("report.jsp").forward(request, response);
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
