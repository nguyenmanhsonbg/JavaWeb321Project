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
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author Admin
 */
public class CongNoController extends HttpServlet {

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
        String raw_id = request.getParameter("id");
        CustomerDAO cuD = new CustomerDAO();
        OrderDAO oD = new OrderDAO();
        if (raw_id != null) {
            int id = Integer.parseInt(raw_id);
            Customer c = cuD.getListCustomerById(id);
              DecimalFormat formatter = new DecimalFormat("###,###,###");
            double totalPaid = oD.totalPaidByCustomerId(id);
            double totalValue = oD.totalOrderValue(id);
            double unpaid = totalValue - totalPaid;
            
            
            String fTotalPaid =    formatter.format(totalPaid);
            String fTotalValue =    formatter.format(totalValue);
            String fUnpaid =    formatter.format(unpaid);
           
  
            request.setAttribute("paids", oD.getAllPaids(id));
            request.setAttribute("Customer", c);
            request.setAttribute("TotalPaid",fTotalPaid );
            request.setAttribute("TotalOrderValue", fTotalValue);
            request.setAttribute("Unpaid",fUnpaid );
            request.getRequestDispatcher("CongNo.jsp").forward(request, response);
            
        }
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
        OrderDAO oD = new OrderDAO();
        CustomerDAO cuD = new CustomerDAO();
        String date = request.getParameter("date");
        String paid = request.getParameter("paid");
        int id=Integer.parseInt(request.getParameter("cusid"));
        if (date != null && paid != null) {
            oD.insertPaidHistory(id,date, Double.parseDouble(paid));
            cuD.updatePaid(id);
            response.sendRedirect("CongNo?id="+id);
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
