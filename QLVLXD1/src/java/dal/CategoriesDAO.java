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
import model.Product;

/**
 *
 * @author Admin
 */
public class CategoriesDAO extends DBContext {
    public ArrayList<Categories> getAllCategories(){
            ArrayList<Categories> listCa = new ArrayList<>();
        try {
            String sql= "select ca.CategoryID,ca.CategoryName,ca.Description,ca.Picture,p.ProductID,p.ProductName,p.MeasureUnit from Categories ca inner join Products p \n" +
"on ca.CategoryID = p.CategoryID\n" +
"group by ca.CategoryID,ca.CategoryName,ca.Description,ca.Picture,p.ProductID,p.ProductName,p.MeasureUnit";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Categories ca = new Categories();
            ca.setId(-1);
            while(rs.next()){
                if(ca.getId() != rs.getInt("CategoryID")){
                    ca = new Categories();
                    ca.setId(rs.getInt("CategoryID"));
                    ca.setName(rs.getString("CategoryName"));
                    ca.setDescription(rs.getString("Description"));
                    ca.setPicture(rs.getString("Picture"));
                    
                    Product p = new Product();
                    p.setId(rs.getInt("ProductID"));
                    p.setName(rs.getString("ProductName"));
                    p.setMeasureUnit(rs.getString("MeasureUnit"));
                    
                    ca.getListProduct().add(p);
                    listCa.add(ca);
                }
            }
            return listCa;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
}
