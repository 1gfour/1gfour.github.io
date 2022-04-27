/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author PhatPH
 */
public class UserError {

    private String userIDError;
    private String userNameError;
    private String roleIDError;
    private String passwordError;
    private String addressError;
    private String birthdayError;
    private String emailError;
    private String phoneNumError;
    private String confirmError;
    private String messageError;

    public UserError() {
    }

    public UserError(String userIDError, String userNameError, String roleIDError, String passwordError, String addressError, String birthdayError, String emailError, String phoneNumError, String confirmError, String messageError) {
        this.userIDError = userIDError;
        this.userNameError = userNameError;
        this.roleIDError = roleIDError;
        this.passwordError = passwordError;
        this.addressError = addressError;
        this.birthdayError = birthdayError;
        this.emailError = emailError;
        this.phoneNumError = phoneNumError;
        this.confirmError = confirmError;
        this.messageError = messageError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getBirthdayError() {
        return birthdayError;
    }

    public void setBirthdayError(String birthdayError) {
        this.birthdayError = birthdayError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneNumError() {
        return phoneNumError;
    }

    public void setPhoneNumError(String phoneNumError) {
        this.phoneNumError = phoneNumError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    
    

}
