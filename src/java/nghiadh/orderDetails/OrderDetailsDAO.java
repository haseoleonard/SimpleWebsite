/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.orderDetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import nghiadh.utils.DBHelper;

/**
 *
 * @author haseo
 */
public class OrderDetailsDAO implements Serializable{
    public boolean insertOrderDetails(int OrderID, Map<String,Integer> items) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm =  null;
        try{
            con = DBHelper.makeConnection();
            con.setAutoCommit(true);
            if(con!=null){
                String sql = "insert into OrderDetails(OrderID,ProductName,Quantity) values(?,?,?)";   
                psm=con.prepareStatement(sql);
                for (Map.Entry<String, Integer> entry : items.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    psm.setInt(1, OrderID);
                    psm.setString(2, key);
                    psm.setInt(3, value);
                    psm.executeUpdate();
                }
                con.commit();
                return true;
            }
        }finally{
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return false;
    }
}
