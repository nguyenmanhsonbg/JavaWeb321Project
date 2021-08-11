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
import model.Import;
import model.ImportDetail;
import model.Product;

/**
 *
 * @author Administrator
 */
public class ImportDetailDAO extends DBContext {
     public void insertImportDetail(ImportDetail odd) {
        try {
            String sql = "insert into [Import Details](ImportID,ProductID,UnitPrice,Quantity,Place,QuantitySuccess,Note) values(?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,odd.getId());
            stm.setInt(2, odd.getProduct().getId());
            stm.setDouble(3, odd.getUnitPrice());
            stm.setDouble(4, odd.getQuantity());
            stm.setString(5, odd.getPlace());
            stm.setDouble(6, odd.getQuantitySuccess());
            stm.setString(7, odd.getNote());
            
           
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public void deleteImportDetailByImportId(int oid) {

        try {
            String sql = "delete from [Import Details] where ImportID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, oid);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         public void deleteExactlyImportDetail(int oid,int pid, String place) {

        try {
            String sql = "DELETE FROM [Import Details] WHERE ImportID=? AND ProductID=? AND Place=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, oid);
            stm.setInt(2, pid);
            stm.setString(3, place);
            
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          public ArrayList<ImportDetail> getListImportDetailByImportId(int orderId) {
                ArrayList<ImportDetail> listOdd = new ArrayList<>();
        try {

            String sql = "select * from [Import Details] o inner join Products p on o.ProductID = p.ProductID\n" +
"where ImportID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            
            
            while (rs.next()) {
                ImportDetail o = new ImportDetail();
                o.setId(orderId);
                o.setQuantity(rs.getDouble("Quantity"));
                o.setUnitPrice(rs.getDouble("UnitPrice"));
                o.setPlace(rs.getString("Place"));
                o.setQuantitySuccess(rs.getDouble("QuantitySuccess"));
                o.setNote(rs.getString("Note"));
                
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setName(rs.getString("ProductName"));
                p.setMeasureUnit(rs.getString("MeasureUnit"));
                
                o.setProduct(p);
                listOdd.add(o);
              
            }
            return listOdd;
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
