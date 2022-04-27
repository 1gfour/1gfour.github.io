/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.ProductDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PhatPH
 */
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {

    private final String PRODUCTMANAGEMENT = "ProductManagement";
    private final String ERROR = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int error = 0, proPrice = 0, quantity = 0;
        ProductDAO dao = new ProductDAO();
        String proID = request.getParameter("productID");
        String proName = request.getParameter("productName");
        if (request.getParameter("price") == null || request.getParameter("quantity") == null) {
            error += 1;
        } else {
            proPrice = Integer.parseInt(request.getParameter("price"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }
        String newPath = request.getParameter("txtNewPath");
        String categoryID = request.getParameter("categoryID");
        String importDate = request.getParameter("importDate");
        String usingDate = request.getParameter("usingDate");
        if (proID == null || proName == null || categoryID == null || usingDate == null || importDate == null || newPath == null) {
            error += 1;
        }
        if (error > 0) {
            response.sendRedirect(ERROR);
            return;
        }
        if (newPath.equalsIgnoreCase("")) {
            newPath = request.getParameter("txtOldPath");
        }
        try {
            dao.updateProduct(proID, proName, proPrice, quantity, categoryID, newPath, importDate, usingDate);
            response.sendRedirect(PRODUCTMANAGEMENT);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
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
