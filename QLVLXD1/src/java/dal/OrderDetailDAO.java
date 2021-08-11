/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categories;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Administrator
 */
public class OrderDetailDAO extends DBContext {
     public void insertOrderDetail(OrderDetail odd) {
        try {
            String sql = "insert into [Order Details](OrderID,ProductID,UnitPrice,Quantity,Place) values(?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,odd.getOrderId());
            stm.setInt(2, odd.getProduct().getId());
            stm.setDouble(3, odd.getUnitPrice());
            stm.setDouble(4, odd.getQuantity());
            stm.setString(5, odd.getPlace());
            
           
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public void deleteOrderDetailByOrderId(int oid) {

        try {
            String sql = "delete from [Order Details] where OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, oid);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         public void deleteExactlyOrderDetail(int oid,int pid, String place) {

        try {
            String sql = "DELETE FROM [Order Details] WHERE OrderID=? AND ProductID=? AND Place=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, oid);
            stm.setInt(2, pid);
            stm.setString(3, place);
            
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          public ArrayList<OrderDetail> getListOrderDetailByOrderId(int orderId) {
                ArrayList<OrderDetail> listOdd = new ArrayList<>();
        try {

            String sql = "select * from [Order Details] o inner join Products p on o.ProductID = p.ProductID\n" +
"where OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            
            
            while (rs.next()) {
                OrderDetail o = new OrderDetail();
                o.setOrderId(orderId);
                o.setQuantity(rs.getDouble("Quantity"));
                o.setUnitPrice(rs.getDouble("UnitPrice"));
                o.setPlace(rs.getString("Place"));
                
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setName(rs.getString("ProductName"));
                p.setMeasureUnit(rs.getString("MeasureUnit"));
                
                o.setProduct(p);
                listOdd.add(o);
              
            }
            return listOdd;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
