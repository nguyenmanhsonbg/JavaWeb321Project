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
import model.Product;

/**
 *
 * @author Administrator
 */
public class ProductDAO extends DBContext{
        public ArrayList<Product> getAllProduct(){
            ArrayList<Product> listP = new ArrayList<>();
        try {
            String sql= "select * from Products";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();    
            while(rs.next()){
            Product p = new Product();
            p.setId(rs.getInt("ProductID"));
            p.setName(rs.getString("ProductName"));
            p.setMeasureUnit(rs.getString("MeasureUnit"));
            
            listP.add(p);
            }
            return listP;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        public Product getProductById(int id){
           Product p = new Product();
        try {
            String sql= "select * from Products where ProductID ="+id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();    
            
            while(rs.next()){
            p = new Product();
            p.setId(rs.getInt("ProductID"));
            p.setName(rs.getString("ProductName"));
            p.setMeasureUnit(rs.getString("MeasureUnit"));
            

            }
            return p;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
