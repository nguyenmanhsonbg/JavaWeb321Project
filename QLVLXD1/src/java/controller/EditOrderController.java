/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CustomerDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Administrator
 */
public class EditOrderController extends HttpServlet {
    ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //get id from edit order
        int oid = Integer.parseInt(request.getParameter("id"));
        
        OrderDAO oD = new OrderDAO();
        Order o = oD.getOrderByOrderId(oid);
        OrderDetailDAO oDD = new OrderDetailDAO();
        listOrderDetail = oDD.getListOrderDetailByOrderId(oid);
        
        double total = 0;
        for (OrderDetail ood : listOrderDetail) {
            total += (ood.getQuantity() * ood.getUnitPrice());
        }
        
        CustomerDAO cuD = new CustomerDAO();
        ProductDAO pD = new ProductDAO();
        ArrayList<Customer> listCu = cuD.getListCustomer();
        ArrayList<Product> listP = pD.getAllProduct();
        
        request.setAttribute("listCustomer", listCu);
        request.setAttribute("listProduct", listP);
        request.setAttribute("order", o);
        request.setAttribute("total", total);
        request.setAttribute("listOrderDetail", listOrderDetail);
        request.getRequestDispatcher("editOrderForm.jsp").forward(request, response);

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
