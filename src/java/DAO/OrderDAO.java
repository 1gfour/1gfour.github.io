/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBUtils;
import DTO.OrderDTO;
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
public class OrderDAO {

    public List<OrderDTO> getOrders() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDTO> list = null;

        try {
            cn = DBUtils.getConnection();
            String sql = "use AssDB\n";
            sql += "select [orderID],[orderDate],[total],[userID],[status] from [dbo].[tblOrder]";

            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    int total = rs.getInt("total");
                    String userID = rs.getString("userID");
                    String status = rs.getString("status");
                    OrderDTO order = new OrderDTO(id, orderDate, total, userID, status);
                    list.add(order);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public OrderDTO getOrder(int orderID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDTO> list = null;
        OrderDTO order = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "use AssDB\n";
            sql += "select [orderID],[orderDate],[total],[userID],[status] from [dbo].[tblOrder] where orderID=?";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, orderID);
            rs = pst.executeQuery();
            if (rs != null) {
                int id = rs.getInt("orderID");
                id += 1;
                String orderDate = rs.getString("orderDate");
                int total = rs.getInt("total");
                String userID = rs.getString("userID");
                String status = rs.getString("status");
                order = new OrderDTO(orderID, orderDate, total, userID, status);
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return order;
    }

    public int newOrder(OrderDTO order) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            String insert = "insert into [dbo].[tblOrder] values(?,?,?,(select userID from [dbo].[tblUsers] where userID = ?),?)";
            pst = cn.prepareStatement(insert);
            pst.setInt(1, order.getOrderID());
            pst.setString(2, order.getOrderDate());
            pst.setInt(3, order.getTotal());
            pst.setString(4, order.getUserID());
            pst.setString(5, order.getStatus());
            return pst.executeUpdate();
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
    }

    public int countOrder() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT COUNT([dbo].[tblOrder].orderID) as count FROM [dbo].[tblOrder]";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                rs.next();
                count = rs.getInt("count");
                return count;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return -1;
    }
}
