/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBUtils;
import DTO.ProductDTO;
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
public class ProductDAO {

    private static final String DELETE = "DELETE tblProduct WHERE productID=?";
    private static final String UPDATE_AFTER_ORDER = "UPDATE tblProduct SET quantity=? where productID=?";

    public List<ProductDTO> getProducts() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = null;

        try {
            cn = DBUtils.getConnection();
            String sql = "use AssDB\n";
            sql += "select [productID],[productName],[image],[price],[quantity], [categoryID],[importDate],[usingDate] from [tblProduct]";

            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("productID");
                    String proName = rs.getString("productName");
                    String imagePath = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    ProductDTO product = new ProductDTO(id, proName, imagePath, price, quantity, categoryID, importDate, usingDate);
                    list.add(product);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public ProductDTO getProduct(int proID) throws NamingException, SQLException {

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductDTO product = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "use AssDB\n";
            sql += "select [productID],[productName],[image],[price],[quantity], [categoryID],[importDate],[usingDate]\n";
            sql += "from [tblProduct]\n";
            sql += "where productID = ?";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, proID);
            rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("productID");
                    String proName = rs.getString("productName");
                    String imagePath = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    product = new ProductDTO(id, proName, imagePath, price, quantity, categoryID, importDate, usingDate);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return product;
    }

    public boolean addProduct(ProductDTO product) throws NamingException, SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.getConnection();
            String insert = "INSERT INTO tblProduct(productID, productName, image, price,quantity,categoryID,importDate,usingDate) VALUES (?,?,?,?,?,?,?,?)";
            pst = cn.prepareStatement(insert);
            pst.setInt(1, product.getProductID());
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getImage());
            pst.setInt(4, product.getPrice());
            pst.setInt(5, product.getQuantity());
            pst.setString(6, product.getCategoryID());
            pst.setString(7, product.getImportDate());
            pst.setString(8, product.getUsingDate());
            check = pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
            return check;
        }
    }

    public int updateProduct(String id, String name, int price, int quantity, String categoryID, String imgPath, String importDate, String usingDate) throws SQLException, NamingException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.getConnection();
            String update = "update [dbo].[tblProduct] SET [productName]=?, [image]=?, [price]=?, [quantity]=?, [categoryID]=?, [importDate]=? , [usingDate]=? where [productID]=?";
            pst = cn.prepareStatement(update);
            pst.setString(1, name);
            pst.setString(2, imgPath);
            pst.setInt(3, price);
            pst.setInt(4, quantity);
            pst.setString(5, categoryID);
            pst.setString(6, importDate);
            pst.setString(7, usingDate);
            pst.setString(8, id);
            pst.executeUpdate();
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return 0;
    }

    public int countPro() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT COUNT([dbo].[tblProduct].productID) as count FROM [dbo].[tblProduct]";
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

    public boolean deteleProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(DELETE);
            stm.setString(1, productID);
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateAfterOrder(int productID, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(UPDATE_AFTER_ORDER);
            stm.setInt(1, quantity);
            stm.setInt(2, productID);
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
