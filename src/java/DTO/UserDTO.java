/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author PhatPH
 */
public class UserDTO {

    private String userID;
    private String fullName;
    private String roleID;
    private String password;
    private String address;
    private String birthday;
    private String phoneNum;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String userID, String fullName, String roleID, String password, String address, String birthday, String phoneNum, String email) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public UserDTO(String userID, String fullName, String address, String phoneNum, String email) {
        this.userID = userID;
        this.fullName = fullName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return fullName;
    }

    public void setUserName(String userName) {
        this.fullName = userName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
