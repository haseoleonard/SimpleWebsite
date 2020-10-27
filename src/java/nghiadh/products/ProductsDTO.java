package nghiadh.products;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author haseo
 */
public class ProductsDTO implements Serializable{
    private String productName;

    public ProductsDTO() {
    }

    public ProductsDTO(String productName) {
        this.productName = productName;
    }
    

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
}
