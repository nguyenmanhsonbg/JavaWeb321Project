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
import model.Paid;
import model.Product;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {

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

    public double totalOrderValue(int id) {
        try {
            String sql = " select SUM(od.UnitPrice*od.Quantity) as total from Customers c inner join Orders o on c.CustomerID = o.CustomerID \n"
                    + " inner join [Order Details] od on o.OrderID = od.OrderID where c.CustomerID = ?";
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

    public ArrayList<Order> getAllOrders(String name, String from, String to) {
        ArrayList<Order> listOrder = new ArrayList<>();
        try {

            String sql = "select cu.CustomerID,cu.CompanyName,cu.Address, o.OrderID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName from Orders o inner join [Order Details] od\n"
                    + "on o.OrderID = od.OrderID inner join Products p on od.ProductID = p.ProductID inner join Categories c on p.CategoryID = c.CategoryID inner join Customers cu on o.CustomerID = cu.CustomerID";

            //search by name,from date to date
            if ((name != null && from != null && to != null) && name != "" && from != "" && to != "") {
                sql += " where cu.CompanyName like '%" + name + "%' AND o.Date Between '" + from + "' AND '" + to + "'";

            } // by name and from date
            else if ((name != null && from != null) && (name != "" && from != "")) {
                sql += " where cu.CompanyName like '" + name + "%' AND o.Date >'" + from + "'";
            } // by name and to date
            else if ((name != null && to != null) && (name != "" && to != "")) {
                sql += " where cu.CompanyName like '" + name + "%' AND o.Date < '" + to + "'";
            } // by name 
            else if (name != null && name != "") {
                sql += " where cu.CompanyName like '" + name + "%' ";
            } else if (from != null && from != "") {
                sql += " where o.Date >= '" + from + "'";
            } else if (to != null && to != "") {
                sql += " where o.Date <= '" + to + "'";
            }

            sql += " group by cu.CustomerID,cu.CompanyName,cu.Address, o.OrderID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName\n"
                    + "order by o.Date desc";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            Order o = new Order();
            o.setId(-1);
            while (rs.next()) {
                if (o.getId() != rs.getInt("OrderID")) {

                    o = new Order();
                    o.setId(rs.getInt("OrderID"));
                    o.setDate(rs.getString("Date"));
                    o.setPicture(rs.getString("Picture"));
                    o.setNote(rs.getString("Note"));
                    o.setValue(rs.getDouble("value"));

                    Customer cu = new Customer();
                    cu.setId(rs.getInt("CustomerID"));
                    cu.setCompanyName(rs.getString("CompanyName"));
                    cu.setAddress(rs.getString("Address"));
                    o.setCustomer(cu);

                    Product p = new Product();
                    p.setId(-1);
                    if (p.getId() != rs.getInt("ProductID"));
                    {
                        Categories c = new Categories();
                        c.setId(rs.getInt("CategoryID"));
                        c.setName(rs.getString("CategoryName"));

                        p.setCategories(c);
                        p.setId(rs.getInt("ProductID"));
                        p.setName(rs.getString("ProductName"));
                        p.setMeasureUnit(rs.getString("MeasureUnit"));

                    }

                    OrderDetail od = new OrderDetail();
                    od.setOrderId(o.getId());
                    od.setUnitPrice(rs.getDouble("UnitPrice"));
                    od.setQuantity(rs.getDouble("Quantity"));
                    od.setProduct(p);
                    od.setPlace(rs.getString("Place"));

                    o.getListOrderDetail().add(od);

                    listOrder.add(o);
                }

            }
            return listOrder;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Order> getAllOrderPT(int pageindex, int pagesize) {
        ArrayList<Order> listOrder = new ArrayList<>();
        try {

            String sql = "select *from(\n"
                    + "SELECT ROW_NUMBER() OVER (ORDER BY o.date desc,o.OrderId desc) as rid ,o.OrderID,o.Date,o.value,cu.CustomerID,cu.CompanyName,cu.Address,sum(od.UnitPrice*od.Quantity) as total\n"
                    + "from Orders o inner join [Order Details] od on o.OrderID = od.OrderID \n"
                    + "inner join Products p on od.ProductID = p.ProductID \n"
                    + "inner join Categories c on p.CategoryID = c.CategoryID inner join Customers cu on o.CustomerID = cu.CustomerID  \n"
                    + "group by  o.OrderID,o.Date,o.value,cu.CustomerID,cu.CompanyName,cu.Address) bigrecord\n"
                    + "WHERE bigrecord.rid >= (? - 1)*? + 1 AND rid <= ? * ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            stm.setInt(4, pageindex);
            ResultSet rs = stm.executeQuery();
            Order o = new Order();
            o.setId(-1);
            while (rs.next()) {
                if (o.getId() != rs.getInt("OrderID")) {

                    o = new Order();
                    o.setId(rs.getInt("OrderID"));
                    o.setDate(rs.getString("Date"));
                    o.setValue(rs.getDouble("total"));
                    Customer cu = new Customer();
                    cu.setId(rs.getInt("CustomerID"));
                    cu.setCompanyName(rs.getString("CompanyName"));
                    cu.setAddress(rs.getString("Address"));
                    o.setCustomer(cu);
                    listOrder.add(o);
                }

            }
            return listOrder;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public int count() {
        ArrayList<Order> listOrder = new ArrayList<>();
        try {

            String sql = "select count(*) as total from(\n"
                    + "SELECT ROW_NUMBER() OVER (ORDER BY o.date desc) as rid ,o.OrderID,o.Date,o.value,cu.CustomerID,cu.CompanyName,cu.Address,sum(od.UnitPrice*od.Quantity) as total\n"
                    + "from Orders o inner join [Order Details] od on o.OrderID = od.OrderID \n"
                    + "inner join Products p on od.ProductID = p.ProductID \n"
                    + "inner join Categories c on p.CategoryID = c.CategoryID inner join Customers cu on o.CustomerID = cu.CustomerID  \n"
                    + "group by  o.OrderID,o.Date,o.value,cu.CustomerID,cu.CompanyName,cu.Address) bigrecord";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            Order o = new Order();
            o.setId(-1);
            while (rs.next()) {
                return (rs.getInt("total"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public Order getOrderByOrderId(int orderId) {
        Order o = new Order();
        o.setId(-1);
        try {

            String sql = "select cu.CustomerID,cu.CompanyName,cu.Address, o.OrderID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName from Orders o inner join [Order Details] od\n"
                    + "on o.OrderID = od.OrderID inner join Products p on od.ProductID = p.ProductID inner join Categories c on p.CategoryID = c.CategoryID inner join Customers cu on o.CustomerID = cu.CustomerID where o.OrderID = ?"
                    + " group by cu.CustomerID,cu.CompanyName,cu.Address, o.OrderID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName\n"
                    + "order by o.Date desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                if (o.getId() != rs.getInt("OrderID")) {

                    o = new Order();
                    o.setId(rs.getInt("OrderID"));
                    o.setDate(rs.getString("Date"));
                    o.setPicture(rs.getString("Picture"));
                    o.setNote(rs.getString("Note"));
                    o.setValue(rs.getDouble("value"));

                    Customer cu = new Customer();
                    cu.setId(rs.getInt("CustomerID"));
                    cu.setCompanyName(rs.getString("CompanyName"));
                    cu.setAddress(rs.getString("Address"));
                    o.setCustomer(cu);

                    Product p = new Product();
                    p.setId(-1);
                    if (p.getId() != rs.getInt("ProductID"));
                    {
                        Categories c = new Categories();
                        c.setId(rs.getInt("CategoryID"));
                        c.setName(rs.getString("CategoryName"));

                        p.setCategories(c);
                        p.setId(rs.getInt("ProductID"));
                        p.setName(rs.getString("ProductName"));
                        p.setMeasureUnit(rs.getString("MeasureUnit"));

                    }

                    OrderDetail od = new OrderDetail();
                    od.setOrderId(o.getId());
                    od.setUnitPrice(rs.getDouble("UnitPrice"));
                    od.setQuantity(rs.getDouble("Quantity"));
                    od.setProduct(p);
                    od.setPlace(rs.getString("Place"));

                    o.getListOrderDetail().add(od);

                }

            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void insertOrder(Order o) {
        try {
            String sql = "insert into Orders(CustomerID,Date,Picture,Note,value) values(?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, o.getCustomer().getId());
            stm.setString(2, o.getDate());
            stm.setString(3, o.getPicture());
            stm.setString(4, o.getNote());
            stm.setDouble(5, o.getValue());

            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertPaidHistory(int id,String date, double money) {
        try {
            String sql = " insert into PaidHistory(CustomerID,Paid,Date) values (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setDouble(2, money);
            stm.setString(3, date);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getLastestId() {
        int id = -1;
        try {

            String sql = "select max(OrderID) as 'lastId' from Orders";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("lastId");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void deleteOrderById(int id) {

        try {
            String sql = "delete from Orders where OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Paid> getAllPaids(int id) {
        ArrayList<Paid> paids = new ArrayList<>();
        try {

            String sql = " select * from PaidHistory where customerId=?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Paid p = new Paid();
                p.setDate(rs.getDate("date"));
                p.setMoney(rs.getDouble("paid"));
                paids.add(p);
            }
            return paids;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
