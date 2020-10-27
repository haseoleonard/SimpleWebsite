package nghiadh.products;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nghiadh.utils.DBHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author haseo
 */
public class ProductsDAO implements Serializable{
    private List<ProductsDTO> productList;

    public List<ProductsDTO> getProductList() {
        return productList;
    }
    
    public void loadProductList() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="select ProductName from Products";
                psm=con.prepareStatement(sql);
                rs=psm.executeQuery();
                while(rs.next()){
                    String productName= rs.getString("ProductName");
                    ProductsDTO dto = new ProductsDTO(productName);
                    if(this.productList==null){
                        this.productList=new ArrayList<>();
                    }
                    this.productList.add(dto);
                }
            }
        }finally{
            if(rs!=null)rs.close();
            if(psm!=null)psm.close();
            if(con!=null) con.close();
        }
    }
}
