/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.cart;

import java.io.Serializable;

/**
 *
 * @author haseo
 */
public class CheckOutError implements Serializable{
    private String customerNameEmptyErr;
    private String customerAddressEmptyErr;

    public String getCustomerNameEmptyErr() {
        return customerNameEmptyErr;
    }

    public void setCustomerNameEmptyErr(String customerNameEmptyErr) {
        this.customerNameEmptyErr = customerNameEmptyErr;
    }

    public String getCustomerAddressEmptyErr() {
        return customerAddressEmptyErr;
    }

    public void setCustomerAddressEmptyErr(String customerAddressEmptyErr) {
        this.customerAddressEmptyErr = customerAddressEmptyErr;
    }
    
}
