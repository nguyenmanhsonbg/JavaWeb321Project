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
import model.Supplier;

/**
 *
 * @author Administrator
 */
public class SupplierDAO extends DBContext{
    
     public ArrayList<Supplier> getListSupplier() {

        ArrayList<Supplier> listCu = new ArrayList<>();
        try {
            String sql = "select * from Suppliers";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Supplier c = new Supplier(rs.getInt("SupplierID"), rs.getString("CompanyName"), rs.getString("ContactName"), rs.getString("Address"), "0" + rs.getInt("Phone"), rs.getDouble("Unpaid"), rs.getDouble("Paid"));
                listCu.add(c);
            }
            return listCu;

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Supplier getListSupplierById(int id) {

        try {
            String sql = "select  * from Suppliers where SupplierID =" + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Supplier c = new Supplier();
            while (rs.next()) {
                c = new Supplier(rs.getInt("SupplierID"), rs.getString("CompanyName"), rs.getString("ContactName"), rs.getString("Address"), "0" + rs.getInt("Phone"), rs.getDouble("Unpaid"), rs.getDouble("Paid"));

            }
            return c;

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

    public void updateSupplier(Supplier c) {
        try {
            String sql = "update  Suppliers  set CompanyName=? , Address=? , Phone=? where SupplierID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCompanyName());
            stm.setString(2, c.getAddress());
            stm.setString(3, c.getPhone());
            stm.setInt(4, c.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double getUnpaidBySupplierId(int id){
        
             
        try {
            String sql = " select SUM(value) as 'Unpaid' from Orders where SupplierID =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Supplier c = new Supplier();
            while (rs.next()) {
                return rs.getDouble("Unpaid");
                
            }
         

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    

    public void updateUnpaid(int id){
        
        double Unpaid = getUnpaidBySupplierId(id);
      
        
         try {
            String sql = "update Suppliers  set Unpaid=? where SupplierID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1,Unpaid);
            stm.setInt(2, id);
      
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public double totalPaidBySupplierId(int id) {
        try {
            String sql = " select Sum(Paid) as total from PaidHistory where SupplierID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     
     public void updatePaid(int id){
            double Paid = totalPaidBySupplierId(id);
      
        
         try {
            String sql = "update Suppliers  set Paid=? where SupplierID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1,Paid);
            stm.setInt(2, id);
      
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public void insertNewSupplier(Supplier c) {
        try {
            String sql = "insert into Suppliers(CompanyName,Address,Phone,Unpaid,Paid) values(?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCompanyName());
            stm.setString(2, c.getAddress());
            stm.setString(3, c.getPhone());
            stm.setDouble(4, 0);
            stm.setDouble(5, 0);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
