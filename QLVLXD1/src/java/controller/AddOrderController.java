/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoriesDAO;
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
import model.Categories;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Administrator
 */
public class AddOrderController extends HttpServlet {

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
        CustomerDAO cuD = new CustomerDAO();
        ProductDAO pD = new ProductDAO();
        ArrayList<Customer> listCu = cuD.getListCustomer();
        ArrayList<Product> listP = pD.getAllProduct();
        Order o = new Order();
        request.getSession().setAttribute("curOrder", o);
        request.setAttribute("listCustomer", listCu);
        request.setAttribute("listProduct", listP);
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
                
                //get Seassion
                Order o = (Order) request.getSession().getAttribute("curOrder");
                int refresh = Integer.parseInt(request.getParameter("refresh"));
                //if select Quay lai
                if(refresh == 1){
                    Order od = new Order();
                    request.getSession().setAttribute("curOrder", od);
                    response.sendRedirect("Order");
                    return;
                }else{
                
                //insert into DB
                //insert Order First
                OrderDAO oD = new OrderDAO();
                oD.insertOrder(o);
                OrderDetailDAO oDD = new OrderDetailDAO();
                for(int i=0; i<o.getListOrderDetail().size();i++){
                    oDD.insertOrderDetail(o.getListOrderDetail().get(i));
                }
                   //update Unpaid for Customer
                int cusId = o.getCustomer().getId();  
                CustomerDAO cuD = new CustomerDAO();
                cuD.updateUnpaid(cusId);
                        
                        //refresh Order
                 Order od = new Order();
                    request.getSession().setAttribute("curOrder", od);
                    response.sendRedirect("Order");
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
