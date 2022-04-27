/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.ProductDAO;
import DTO.ProductDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PhatPH
 */
@WebServlet(name = "UpdateCartController", urlPatterns = {"/UpdateCartController"})
public class UpdateCartController extends HttpServlet {

    private final String VIEW_CART_PAGE = "viewCart.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);

        HashMap<ProductDTO, Integer> cart = (HashMap<ProductDTO, Integer>) session.getAttribute("CART");
       
        ProductDAO productDAO = new ProductDAO();
        returnToCurrentPage(cart, response);
        
        boolean edited = false; 
        Integer productID = Integer.valueOf(request.getParameter("productID"));
        List<ProductDTO> removeProducts = new ArrayList<>();
        
        for (ProductDTO product : cart.keySet()) {
           
            if (action.equals("Update")) {
                if (productID.equals(product.getProductID())) {    
                     int quantity = 1;
            if (!request.getParameter("Quantity").equals("")) {
                quantity = Integer.parseInt(request.getParameter("Quantity"));
            }
                    if (quantity <= 0) {
                        quantity = 0;
                        action = "Remove";
                    } else {
                        if (productID.equals(product.getProductID())) {
                            cart.put(product, quantity);
                        }
                    }
                }
                edited = true;
            }
            if (action.equals("Remove")) {
                if (productID.equals(product.getProductID())) {
                    removeProducts.add(product);
                }
                edited = true;
            }
        }
        
        if (edited == true) {
            removeProducts.forEach(cart::remove);
            session.setAttribute("CART", cart);
            response.sendRedirect(VIEW_CART_PAGE);
        }

    }

    private void returnToCurrentPage(HashMap<ProductDTO, Integer> cart, HttpServletResponse response) throws IOException {
        if (cart == null || cart.isEmpty()) 
            response.sendRedirect(VIEW_CART_PAGE);
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
