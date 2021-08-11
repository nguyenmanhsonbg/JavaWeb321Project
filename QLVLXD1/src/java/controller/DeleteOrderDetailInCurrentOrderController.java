/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author Administrator
 */
public class DeleteOrderDetailInCurrentOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Order o = (Order) request.getSession().getAttribute("curOrder");
        ArrayList<OrderDetail> listOdd = o.getListOrderDetail();
        ProductDAO pD = new ProductDAO();
        int oid = o.getId();
        int pid = Integer.parseInt(request.getParameter("pid"));
        String place = request.getParameter("place");
        //delete odd
        for (int i = 0; i < listOdd.size(); i++) {
            if (listOdd.get(i).getOrderId() == oid && listOdd.get(i).getProduct().getId() == pid && listOdd.get(i).getPlace().equalsIgnoreCase(place)) {
                listOdd.remove(i);
            }
        }

        //update list to order
        o.setListOrderDetail(listOdd);
        double value = 0;
        //set new value for order
        for (int i = 0; i < o.getListOrderDetail().size(); i++) {
            value += o.getListOrderDetail().get(i).getQuantity() * o.getListOrderDetail().get(i).getUnitPrice();
        }
        //set new value
        o.setValue(value);

        //set session again
        request.getSession().setAttribute("curOrder", o);
        //redirect to oldpage
        request.setAttribute("listProduct", pD.getAllProduct());
        request.setAttribute("curOrder", o);
        response.sendRedirect("orderForm.jsp");

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
