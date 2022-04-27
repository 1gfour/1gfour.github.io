/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import DTO.ProductDTO;
import DTO.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PhatPH
 */
public class ProcessOrderController extends HttpServlet {

    private final String PRODUCT_PAGE = "product.jsp";
    private final String VIEW_CART_PAGE = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        HashMap<ProductDTO, Integer> cart = (HashMap<ProductDTO, Integer>) session.getAttribute("CART");
        
        if(!action.equals("ADD ORDER")){
            response.sendRedirect(VIEW_CART_PAGE);
        } 
        returnToCurrentPageIfCartIsNull(cart, response);
        
        OrderDTO orderDto = null;
        OrderDAO orderDAO = new OrderDAO();

        int total = 0;
        for (ProductDTO product : cart.keySet()) {
            total = total + (product.getPrice() * cart.get(product));
        }
        
        Date date = new Date();
        String orderDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        try {
            UserDTO userDto = (UserDTO) session.getAttribute("LOGIN_USER");
            int newOrderId = orderDAO.countOrder() + 1;
            orderDto = new OrderDTO(newOrderId, orderDate, total, userDto.getUserID(), "1");
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(ProcessOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        try {
            orderDAO.newOrder(orderDto);
            for (ProductDTO product : cart.keySet()) {
                int newOrderDetailId = orderDetailDAO.countOrderDetail() + 1;
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(newOrderDetailId, product.getPrice(),cart.get(product), orderDto.getOrderID(), product.getProductID());
                orderDetailDAO.newOrderDetail(orderDetailDTO);
            }

        } catch (NamingException | SQLException ex) {
            Logger.getLogger(ProcessOrderController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            response.sendRedirect(PRODUCT_PAGE);
            session = request.getSession(true);
            session.setAttribute("CART", null);
        }
    }
    
    private void returnToCurrentPageIfCartIsNull(HashMap<ProductDTO, Integer> cart, HttpServletResponse response) throws IOException {
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
