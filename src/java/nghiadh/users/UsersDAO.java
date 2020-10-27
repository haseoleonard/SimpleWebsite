/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nghiadh.utils.DBHelper;

/**
 *
 * @author haseo
 */
public class UsersDAO implements Serializable{
    public boolean checkLogin(String username,String password) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        String sql = "Select username,password "
                + "from Users "
                + "where username like ? and password like ?";
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                psm = con.prepareStatement(sql);
                psm.setString(1, username);
                psm.setString(2, password);
                rs = psm.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        }finally{
            if(rs!=null)rs.close();
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return false;
    }
    
    public String getFullNameByUsername(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql = "Select lastName from Users where username like ?";
                psm=con.prepareStatement(sql);
                psm.setString(1, username);
                rs = psm.executeQuery();
                if(rs.next()){
                    String fullname = rs.getString("lastName");
                    return fullname;
                }
            }
        }finally{
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return null;
    }
    
    private List<UsersDTO> userList;

    public List<UsersDTO> getUserList() {
        return userList;
    }
    
    public void searchUserByLastName(String lastName) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            con = DBHelper.makeConnection();
            String sql = "Select username,password,lastName,isAdmin "
                    + "from Users "
                    + "Where lastName like ?";
            if(con!=null){
                psm = con.prepareStatement(sql);
                psm.setString(1, "%"+lastName+"%");
                rs = psm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");
                    UsersDTO dto = new UsersDTO(username, password, fullname, role);
                    if(this.userList==null){
                        this.userList = new ArrayList<UsersDTO>();
                    }
                    this.userList.add(dto);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(psm!=null) psm.close();
            if(con!=null) con.close();
        }
    }
    
    public boolean deleteUserByUsername(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm = null;
        try{
            con = DBHelper.makeConnection();
            if(con!=null){
                String sql = "Delete from Users where username like ?";
                psm =con.prepareStatement(sql);
                psm.setString(1, username);
                int rs = psm.executeUpdate();
                if(rs>0)
                    return true;
            }
        }finally{
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return false;
    }
    
    public boolean updateAccount(String username, String password,String fullname,boolean admin) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm = null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql = "update Users set password=?, lastName=?, isAdmin=? where username like ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, password);
                psm.setString(2, fullname);
                psm.setBoolean(3, admin);
                psm.setString(4, username);
                int rs = psm.executeUpdate();
                if(rs>0){
                    return true;
                }
            }
        }finally{
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return false;
    }
    
    public boolean createNewAccount(String username,String password,String fullName,boolean isAdmin) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm = null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql = "Insert into Users(username,password,lastName,isAdmin) "
                        + "values(?,?,?,?)";
                psm = con.prepareStatement(sql);
                psm.setString(1, username);
                psm.setString(2, password);
                psm.setNString(3, fullName);
                psm.setBoolean(4, isAdmin);
                int rs = psm.executeUpdate();
                if(rs>0) return true;
            }
        }finally{
            if(con!=null)con.close();
        }
        return false;
    }
}
