/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBUtils;
import DTO.OrderDetailDTO;
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
public class OrderDetailDAO {

    public List<OrderDetailDTO> getOrderDetails(String orderID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDetailDTO> list = null;

        try {
            cn = DBUtils.getConnection();
            String sql = "use AssDB\n";
            sql += "select [detailId],[productID],[quantity],[price] from [dbo].[tblOrderDetail] where orderID=?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, orderID);
            rs = pst.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    int detailId, quantity, productID, price;
                    detailId = rs.getInt("detailId");
                    productID = rs.getInt("productID");
                    quantity = rs.getInt("quantity");
                    price = rs.getInt("price");
                    OrderDetailDTO detail = new OrderDetailDTO(detailId, price, quantity, Integer.parseInt(orderID), productID);
                    list.add(detail);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public int newOrderDetail(OrderDetailDTO orderDetail) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            String insert = "Insert into [dbo].[tblOrderDetail] Values(?,?,?,(select orderID from [dbo].[tblOrder] where orderID=?),?)";
            pst = cn.prepareStatement(insert);
            pst.setInt(1, orderDetail.getDetailID());
            pst.setInt(2, orderDetail.getPrice());
            pst.setInt(3, orderDetail.getQuantity());
            pst.setInt(4, orderDetail.getOrderID());
            pst.setInt(5, orderDetail.getProductID());

            return pst.executeUpdate();
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
    }

    public int getQuantity(String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            String select = "select quantity from [dbo].[tblOrderDetail] where detailID=?";
            pst = cn.prepareStatement(select);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs != null) {
                rs.next();
                int quantity = rs.getInt("quantity");
                return quantity;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return -1;
    }

    public int getPrice(String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            String select = "select price from [dbo].[tblOrderDetail] where detailID=?";
            pst = cn.prepareStatement(select);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs != null) {
                rs.next();
                int price = rs.getInt("price");
                return price;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return -1;
    }

    public int countOrderDetail() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT COUNT([dbo].[tblOrderDetail].orderID) as count FROM [dbo].[tblOrderDetail]";
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
