/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.ProductDAO;
import DTO.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PhatPH
 */
public class AddProductController extends HttpServlet {

    private String ADDPRODUCT = "addProduct.jsp";
    private String SUCCESS = "ProductManagement";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = 0, price = 0, quantity = 0, error = 0;
        String name = request.getParameter("productName");
        String price_tmp = request.getParameter("price");
        String quantity_tmp = request.getParameter("quantity");
        String image = request.getParameter("image");
        String categoryID = request.getParameter("categoryID");
        String importDate = request.getParameter("importDate");
        String usingDate = request.getParameter("usingDate");

        String url = ADDPRODUCT;

        if (name == null || !name.matches("[a-zA-Z0-9-\\s]{3,50}")) {//CHECK NAME
            if (error == 0) {
                url += "?productName=null";
            } else {
                url += "&productName=null";
            }
            error += 1;
        }
        if (price_tmp == null || price_tmp.matches("d{10}")) {//CHECK PRICE
            if (error == 0) {
                url += "?price=null";
            } else {
                url += "&price=null";
            }
            error += 1;
        } else {
            price = Integer.parseInt(price_tmp);
        }
        if (quantity_tmp == null || quantity_tmp.matches("d{10}")) {//CHECK quantity
            if (error == 0) {
                url += "?quantity=null";
            } else {
                url += "&quantity=null";
            }
            error += 1;
        } else {
            quantity = Integer.parseInt(quantity_tmp);
        }
        if (image == null) {//CHECK IMAGE
            if (error == 0) {
                url += "?image=null";
            } else {
                url += "&image=null";
            }
            error += 1;
        }
        if (categoryID == null || !categoryID.matches("VEG||TU")) {//CHECK categoryID
            if (error == 0) {
                url += "?categoryID=null";
            } else {
                url += "&categoryID=null";
            }
            error += 1;
        }
        if (importDate == null) {//CHECK importDate
            if (error == 0) {
                url += "?importDate=null";
            } else {
                url += "&importDate=null";
            }
            error += 1;
        }
        if (usingDate == null) {//CHECK usingDate
            if (error == 0) {
                url += "?usingDate=null";
            } else {
                url += "&usingDate=null";
            }
            error += 1;
        }
        if (error > 0) {
            response.sendRedirect(url);
            return;
        }

        url = SUCCESS;
        ProductDAO dao = new ProductDAO();
        try {
            ProductDTO product = new ProductDTO(dao.countPro() + 1, name, image, price, quantity, categoryID, importDate, usingDate);
            dao.addProduct(product);
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(SUCCESS);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
