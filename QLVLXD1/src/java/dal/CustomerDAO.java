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
import model.Customer;

/**
 *
 * @author Administrator
 */
public class CustomerDAO extends DBContext {

    public ArrayList<Customer> getListCustomer() {

        ArrayList<Customer> listCu = new ArrayList<>();
        try {
            String sql = "select * from Customers";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer c = new Customer(rs.getInt("CustomerID"), rs.getString("CompanyName"), rs.getString("ContactName"), rs.getString("Address"), "0" + rs.getInt("Phone"), rs.getDouble("Unpaid"), rs.getDouble("Paid"));
                listCu.add(c);
            }
            return listCu;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getListCustomerById(int id) {

        try {
            String sql = "select  * from Customers where CustomerID =" + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Customer c = new Customer();
            while (rs.next()) {
                c = new Customer(rs.getInt("CustomerID"), rs.getString("CompanyName"), rs.getString("ContactName"), rs.getString("Address"), "0" + rs.getInt("Phone"), rs.getDouble("Unpaid"), rs.getDouble("Paid"));

            }
            return c;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateCustomer(Customer c) {
        try {
            String sql = "update  Customers  set CompanyName=? , Address=? , Phone=? where CustomerID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCompanyName());
            stm.setString(2, c.getAddress());
            stm.setString(3, c.getPhone());
            stm.setInt(4, c.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getUnpaidByCustomerId(int id) {

        try {
            String sql = " select SUM(value) as 'Unpaid' from Orders where CustomerID =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Customer c = new Customer();
            while (rs.next()) {
                return rs.getDouble("Unpaid");

            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateUnpaid(int id) {

        double Unpaid = getUnpaidByCustomerId(id);

        try {
            String sql = "update Customers  set Unpaid=? where CustomerID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1, Unpaid);
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double totalPaidByCustomerId(int id) {
        try {
            String sql = " select Sum(Paid) as total from PaidHistory where CustomerID=?";
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

    public void updatePaid(int id) {
        double Paid = totalPaidByCustomerId(id);

        try {
            String sql = "update Customers  set Paid=? where CustomerID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1, Paid);
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertNewCustomer(Customer c) {
        try {
            String sql = "insert into Customers(CompanyName,Address,Phone,Unpaid,Paid) values(?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCompanyName());
            stm.setString(2, c.getAddress());
            stm.setString(3, c.getPhone());
            stm.setDouble(4, 0);
            stm.setDouble(5, 0);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
