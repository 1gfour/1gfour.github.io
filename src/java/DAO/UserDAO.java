/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBUtils;
import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author PhatPH
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, roleID, address, birthday, phone, email  FROM tblUsers WHERE userID=? AND password=?";
    private static final String SEARCH = "SELECT userID, fullName, roleID FROM tblUsers WHERE fullName like ?";
    private static final String DELETE = "DELETE tblUsers WHERE userID=?";
    private static final String UPDATE = "UPDATE tblUsers SET fullName=?, roleID=? WHERE userID=?";
    private static final String DUPLICATE = "SELECT userID FROM tblUsers WHERE userID=?";
    private static final String INSERT = "INSERT INTO tblUsers(userID, fullName, roleID, password) VALUES (?,?,?,?)";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String address = rs.getString("address");
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    user = new UserDTO(userID, fullName, roleID, "", address, birthday, phone, email);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList(); // Arraylist()<>;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String password = "***";
                    String roleID = rs.getString("roleID");
                    String address = rs.getString("address");
                    String birthday = rs.getString("birthday");
                    String phoneNum = rs.getString("phoneNum");
                    String email = rs.getString("email");
                    list.add(new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNum, email));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                int value = ptm.executeUpdate();
                check = value > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getUserName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getUserID());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insert(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(INSERT);
            ptm.setString(1, user.getUserID());
            ptm.setString(2, user.getUserName());
            ptm.setString(3, user.getRoleID());
            ptm.setString(4, user.getPassword());
            check = ptm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
            return check;
        }
    }

    public UserDTO getUserByFullNameAndAddress(String fullName, String address) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            String sql = "Select userID, fullName, address, email, phone from [dbo].[tblUsers] where fullName = ? and address= ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, fullName);
            pst.setString(2, address);

            rs = pst.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String name = rs.getString("fullName");
                String addr = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                UserDTO us = new UserDTO(userID, fullName, address, phone, email);
                return us;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return null;
    }

    public UserDTO getUserByUserId(String userID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO usr = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "use AssDB\n";
            sql += "select [userID],[password],[fullName], [roleID], [address], [birthday], [phone],[email]\n";
            sql += "from [tblUsers]\n";
            sql += "where userID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, userID);
            rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String us, pw, fname, role, address, birthday, phoneNum,email;
                    us = rs.getString("userID");
                    pw = rs.getString("password");
                    fname = rs.getString("fullName");
                    role = rs.getString("roleID");
                    address = rs.getString("address");
                    birthday = rs.getString("birthday");
                    phoneNum = rs.getString("phone");
                    email = rs.getString("email");
                    usr = new UserDTO(userID, fname, role, pw, address, birthday, phoneNum, email);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return usr;
    }

}
