/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

/**
 *
 * @author PhatPH
 */
public class Validation {

    public static boolean checkUsername(String username) {
        return username.matches("[a-z0-9]{5-15}");
    }

    public static boolean checkPassword(String password) {
        return password.matches("[a-zA-Z0-9!@#$%^&*()_-]{5,20}");
    }

    public static boolean checkFullname(String fullname) {
        return fullname.matches("[a-zA-Z\\s]{5,30}");
    }

    public static boolean checkAddress(String address) {
        return address.matches("[a-zA-Z0-9\\s/]{5,30}");
    }

    public static boolean checkEmail(String email) {
        return email.matches("[a-zA-Z0-9_-]{3,30}@[a -zA-Z0-9-]{3,30}[.][a-z0-9]{2,5}(.vn)?");
    }

    public static boolean checkPhoneNumber(String phoneNum) {
        return phoneNum.matches("[0-9]{10}");
    }
}
