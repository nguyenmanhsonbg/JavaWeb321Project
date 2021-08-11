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
import model.Account;
import model.Feature;
import model.Group;

/**
 *
 * @author Administrator
 */
public class BaseDAO extends DBContext {
   public Account getByUsernameAndPassword(String username, String password)
    {
        try {
            String sql = "		SELECT a.username,a.password,case when g.id IS NULL then -1 else g.id end as 'gid',g.name,f.fid,f.url FROM \n" +
"                    Account a LEFT JOIN GroupAccount ga ON a.username = ga.username                   \n" +
"					LEFT JOIN [Group] g ON ga.gid = g.id\n" +
"                    		LEFT JOIN FeatureGroup gf ON gf.gid = g.id \n" +
"                 			LEFT JOIN Feature f ON f.fid = gf.fid \n" +
"                 WHERE a.username = ? AND a.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Account account = null;
            Group g = new Group();
            g.setId(-1);
            while(rs.next())
            {
                 if(account == null)
                {
                    account = new Account();
                    account.setUsername(username);
                    account.setPassword(password);
                }
                int gid = rs.getInt("gid");
                if(gid!=-1)
                {
                    if(gid != g.getId())
                    {
                        g = new Group();
                        g.setId(rs.getInt("gid"));
                        g.setName(rs.getString("name"));
                        account.getListGroup().add(g);
                    }
                    Feature f = new Feature();
                    f.setFid(rs.getInt("fid"));
                    f.setUrl(rs.getString("url"));
                    g.getListFeature().add(f);
                }
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
