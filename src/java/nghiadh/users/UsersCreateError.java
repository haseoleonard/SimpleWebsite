/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.users;

import java.io.Serializable;

/**
 *
 * @author haseo
 */
public class UsersCreateError implements Serializable{
    private String usernameLengthErr;
    private String usernameContainsWrongCharsErr;
    private String passwordLengthErr;
    private String passwordContainsWrongCharsErr;
    private String confirmNotMatchedErr;
    private String fullNameLengthErr;
    private String usernameIsExisted;

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getUsernameContainsWrongCharsErr() {
        return usernameContainsWrongCharsErr;
    }

    public void setUsernameContainsWrongCharsErr(String usernameContainsWrongCharsErr) {
        this.usernameContainsWrongCharsErr = usernameContainsWrongCharsErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getPasswordContainsWrongCharsErr() {
        return passwordContainsWrongCharsErr;
    }

    public void setPasswordContainsWrongCharsErr(String passwordContainsWrongCharsErr) {
        this.passwordContainsWrongCharsErr = passwordContainsWrongCharsErr;
    }

    public String getConfirmNotMatchedErr() {
        return confirmNotMatchedErr;
    }

    public void setConfirmNotMatchedErr(String confirmNotMatchedErr) {
        this.confirmNotMatchedErr = confirmNotMatchedErr;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
}
