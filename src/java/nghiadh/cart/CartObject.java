/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author haseo
 */
public class CartObject implements Serializable{
    private Map<String,Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String productName){
        if(this.items==null){
            this.items=new HashMap<>();
        }
        int quantity = 1;
        if(this.items.containsKey(productName)){
            quantity+=this.items.get(productName);
        }
        
        this.items.put(productName, quantity);
    }
    
    public void removeItemFromCart(String productName){
        if(this.items.containsKey(productName)){
            this.items.remove(productName);
        }
        if(this.items.isEmpty()){
            this.items=null;
        }
    }
}
