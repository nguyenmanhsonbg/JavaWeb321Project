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
import model.Supplier;
import model.Import;
import model.Import;
import model.ImportDetail;
import model.Paid;
import model.Product;

/**
 *
 * @author Admin
 */
public class ImportDAO extends DBContext {

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
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double totalImportValue(int id) {
        try {
            String sql = " select SUM(od.UnitPrice*od.Quantity) as total from Suppliers c inner join Good Imports o on c.SupplierID = o.SupplierID \n"
                    + " inner join [Good Import Details] od on o.ImportID = od.ImportID where c.SupplierID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Import> getAllImports(String name, String from, String to) {
        ArrayList<Import> listImport = new ArrayList<>();
        try {

            String sql = "select su.SupplierID,su.CompanyName,su.Address, o.GoodImportID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.QuantitySuccess,od.Note,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName from Good Imports o inner join [Good Import Details] od\n"
                    + "on o.ImportID = od.ImportID inner join Products p on od.ProductID = p.ProductID inner join Categories c on p.CategoryID = c.CategoryID inner join Suppliers cu on o.SupplierID = cu.SupplierID";

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

            sql += " group by cu.SupplierID,cu.CompanyName,cu.Address, o.ImportID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName\n"
                    + "order by o.Date desc";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            Import o = new Import();
            o.setId(-1);
            while (rs.next()) {
                if (o.getId() != rs.getInt("ImportID")) {

                    o = new Import();
                    o.setId(rs.getInt("ImportID"));
                    o.setDate(rs.getString("Date"));
                    o.setPicture(rs.getString("Picture"));
                    o.setNote(rs.getString("Note"));
                    o.setValue(rs.getDouble("value"));

                    Supplier cu = new Supplier();
                    cu.setId(rs.getInt("SupplierID"));
                    cu.setCompanyName(rs.getString("CompanyName"));
                    cu.setAddress(rs.getString("Address"));
                    o.setSupplier(cu);

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

                    ImportDetail od = new ImportDetail();
                    od.setId(o.getId());
                    od.setUnitPrice(rs.getDouble("UnitPrice"));
                    od.setQuantity(rs.getDouble("Quantity"));
                    od.setProduct(p);
                    od.setPlace(rs.getString("Place"));

                    o.getListImportDetail().add(od);

                    listImport.add(o);
                }

            }
            return listImport;
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Import> getAllImportPT(int pageindex, int pagesize) {
        ArrayList<Import> listImport = new ArrayList<>();
        //paging 
        try {
            String sql = "select *from(\n"
                    + "SELECT ROW_NUMBER() OVER (ORDER BY o.date desc,o.ImportId desc) as rid ,o.ImportID,o.Date,o.value,cu.SupplierID,cu.CompanyName,cu.Address,sum(od.UnitPrice*od.Quantity) as total\n"
                    + "from Imports o inner join [Import Details] od on o.ImportID = od.ImportID \n"
                    + "inner join Products p on od.ProductID = p.ProductID \n"
                    + "inner join Categories c on p.CategoryID = c.CategoryID inner join Suppliers cu on o.SupplierID = cu.SupplierID  \n"
                    + "group by  o.ImportID,o.Date,o.value,cu.SupplierID,cu.CompanyName,cu.Address) bigrecord\n"
                    + "WHERE bigrecord.rid >= (? - 1)*? + 1 AND rid <= ? * ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            stm.setInt(4, pageindex);
            ResultSet rs = stm.executeQuery();
            Import o = new Import();
            o.setId(-1);
            while (rs.next()) {
                if (o.getId() != rs.getInt("ImportID")) {

                    o = new Import();
                    o.setId(rs.getInt("ImportID"));
                    o.setDate(rs.getString("Date"));
                    o.setValue(rs.getDouble("total"));
                    Supplier cu = new Supplier();
                    cu.setId(rs.getInt("SupplierID"));
                    cu.setCompanyName(rs.getString("CompanyName"));
                    cu.setAddress(rs.getString("Address"));
                    o.setSupplier(cu);
                    listImport.add(o);
                }

            }
            return listImport;
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public int count() {
        ArrayList<Import> listImport = new ArrayList<>();
        try {

            String sql = "select count(*) as total from(\n"
                    + "SELECT ROW_NUMBER() OVER (ORDER BY o.date desc) as rid ,o.ImportID,o.Date,o.value,cu.SupplierID,cu.CompanyName,cu.Address,sum(od.UnitPrice*od.Quantity) as total\n"
                    + "from Imports o inner join [Import Details] od on o.ImportID = od.ImportID \n"
                    + "inner join Products p on od.ProductID = p.ProductID \n"
                    + "inner join Categories c on p.CategoryID = c.CategoryID inner join Suppliers cu on o.SupplierID = cu.SupplierID  \n"
                    + "group by  o.ImportID,o.Date,o.value,cu.SupplierID,cu.CompanyName,cu.Address) bigrecord";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            Import o = new Import();
            o.setId(-1);
            while (rs.next()) {
                return (rs.getInt("total"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public Import getImportByImportId(int orderId) {
        Import o = new Import();
        o.setId(-1);
        try {

            String sql = "select cu.SupplierID,cu.CompanyName,cu.Address, o.ImportID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName from Imports o inner join [Import Details] od\n"
                    + "on o.ImportID = od.ImportID inner join Products p on od.ProductID = p.ProductID inner join Categories c on p.CategoryID = c.CategoryID inner join Suppliers cu on o.SupplierID = cu.SupplierID where o.ImportID = ?"
                    + " group by cu.SupplierID,cu.CompanyName,cu.Address, o.ImportID,o.Date,o.Picture,o.Note,o.value,od.UnitPrice,od.Quantity,od.Place,p.ProductID,p.ProductName,p.MeasureUnit, c.CategoryID,c.CategoryName\n"
                    + "order by o.Date desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                if (o.getId() != rs.getInt("ImportID")) {

                    o = new Import();
                    o.setId(rs.getInt("ImportID"));
                    o.setDate(rs.getString("Date"));
                    o.setPicture(rs.getString("Picture"));
                    o.setNote(rs.getString("Note"));
                    o.setValue(rs.getDouble("value"));

                    Supplier cu = new Supplier();
                    cu.setId(rs.getInt("SupplierID"));
                    cu.setCompanyName(rs.getString("CompanyName"));
                    cu.setAddress(rs.getString("Address"));
                    o.setSupplier(cu);

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

                    ImportDetail od = new ImportDetail();
                    od.setId(o.getId());
                    od.setUnitPrice(rs.getDouble("UnitPrice"));
                    od.setQuantity(rs.getDouble("Quantity"));
                    od.setProduct(p);
                    od.setPlace(rs.getString("Place"));
                    od.setQuantitySuccess(rs.getDouble("QuantitySuccess"));
                    od.setNote(rs.getString("Note"));

                    o.getListImportDetail().add(od);

                }

            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void insertImport(Import o) {
        try {
            String sql = "insert into Imports(SupplierID,Date,Picture,Note,value) values(?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, o.getSupplier().getId());
            stm.setString(2, o.getDate());
            stm.setString(3, o.getPicture());
            stm.setString(4, o.getNote());
            stm.setDouble(5, o.getValue());

            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertPaidHistory(int id, String date, double money) {
        try {
            String sql = " insert into PaidHistory(SupplierID,Paid,Date) values (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setDouble(2, money);
            stm.setString(3, date);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getLastestId() {
        int id = -1;
        try {

            String sql = "select max(ImportID) as 'lastId' from Imports";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("lastId");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void deleteImportById(int id) {

        try {
            String sql = "delete from Imports where ImportID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
