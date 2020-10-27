/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import nghiadh.orderDetails.OrderDetailsDAO;
import nghiadh.utils.DBHelper;

/**
 *
 * @author haseo
 */
public class OrdersDAO implements Serializable {
    public int getOrderID(String customerName,String customerAddress) throws SQLException, NamingException{
        int ordID=-1;
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            con = DBHelper.makeConnection();
            if(con!=null){
                String sql ="Select OrderID "
                        + "from Orders "
                        + "where CustomerName like ? and CustomerAddress like ?";
                psm = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                psm.setString(1, customerName);
                psm.setString(2, customerAddress);
                rs = psm.executeQuery();
                if(rs.last()){
                    ordID=rs.getInt("OrderID");
                }
            }
        }finally{
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return ordID;
    }
    //
    public boolean insertOrder(String customerName, String customerAddress, Map<String, Integer> items) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement psm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Orders(CustomerName,CustomerAddress) values(?,?)";
                psm = con.prepareStatement(sql);
                psm.setString(1, customerName);
                psm.setString(2, customerAddress);
                int rs = psm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
