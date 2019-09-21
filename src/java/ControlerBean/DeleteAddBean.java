
package ControlerBean;

import Conn.ConnectDb;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Supun Randunu
 */
public class DeleteAddBean {
     Connection conn=ConnectDb.getConnection();
   
        
       public void deleteAdd(String id){
            try {
            Statement stmt = conn.createStatement();
            String sql="DELETE FROM `postadd` WHERE num='"+id+"'";
            stmt.executeUpdate(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DeleteCartBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
}
