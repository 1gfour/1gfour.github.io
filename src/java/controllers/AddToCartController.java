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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private final String PRODUCT_PAGE = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //lấy mã SP
        ProductDTO product = null;
        ProductDAO dao = new ProductDAO();
        String proID = request.getParameter("productID");
        if (proID == null) {
            response.sendRedirect(PRODUCT_PAGE);
            return;
        }
        try {
            product = dao.getProduct(Integer.parseInt(proID));
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(AddToCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //count product
        int count = Integer.parseInt(request.getParameter("count"));
        ++count;
        if (proID != null) {
            //lấy HashMap trong Session ra
            HttpSession session = request.getSession(true);
            HashMap<ProductDTO, Integer> cart = (HashMap<ProductDTO, Integer>) session.getAttribute("CART");
            int quantity = 1;
            if (cart == null) { // Nothing inside
                cart = new HashMap<>();
                cart.put(product, quantity);
                //create cart
                session.setAttribute("CART", cart);
            } else { //Something inside
                Set<ProductDTO> pro = cart.keySet();
                Iterator<ProductDTO> it = pro.iterator();
                while (it.hasNext()) {
                    ProductDTO id = it.next();
                    if (id.getProductID() == Integer.parseInt(proID)) { //Find exactly product ID and quantity++
                        quantity = cart.get(id);
                        quantity++;
                        product = id;
                        break;
                    }
                }
                cart.put(product, quantity);
                //update cart
                session.setAttribute("CART", cart);
            }
           response.sendRedirect(PRODUCT_PAGE + "?count=" + count);
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
