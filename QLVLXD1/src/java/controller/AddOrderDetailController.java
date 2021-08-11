/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Administrator
 */
public class AddOrderDetailController extends HttpServlet {

  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //get Current Order
        Order o = (Order) request.getSession().getAttribute("curOrder");
        ProductDAO pD = new ProductDAO();

        ArrayList<Product> lstP = pD.getAllProduct();
        //get new OrderDetail
        int oid = o.getId();
        int pid = Integer.parseInt(request.getParameter("pid"));
        double quantity = Double.parseDouble(request.getParameter("soluong"));
        double unitPrice = Double.parseDouble(request.getParameter("dongia"));
        String place = request.getParameter("diadiem");
       
        Product p = pD.getProductById(pid);
    
        //set Constructor for new Odt
        OrderDetail odt = new OrderDetail(oid, p, unitPrice, quantity, place);

        //add new odt to list odt of cur Order
        o.getListOrderDetail().add(odt);
        double value = 0;
        //set new value for order
        for (int i = 0; i < o.getListOrderDetail().size(); i++) {
            value += o.getListOrderDetail().get(i).getQuantity() * o.getListOrderDetail().get(i).getUnitPrice();
        }
        //set new value
        o.setValue(value);

        //update session
        request.getSession().setAttribute("curOrder", o);
        request.setAttribute("listProduct", lstP);
        request.setAttribute("curOrder", o);
        request.getRequestDispatcher("orderForm.jsp").forward(request, response);

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
