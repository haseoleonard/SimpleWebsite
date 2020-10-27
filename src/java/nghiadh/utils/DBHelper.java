/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author haseo
 */
public class DBHelper {
    public static Connection makeConnection() throws NamingException, SQLException{
        Connection con = null;
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        DataSource datasource = (DataSource) tomcatContext.lookup("DB");
        con = datasource.getConnection();
        return con;
    }
}
