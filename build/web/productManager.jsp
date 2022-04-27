<%-- 
    Document   : productManager
    Created on : Mar 11, 2022, 7:48:22 PM
    Author     : PhatPH
--%>
<%@page import="DAO.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/05f194fd2c.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <title>Product Manager</title>
    </head>
    <body>
        <h2 style="text-align: center">PRODUCT MANAGEMENT</h2>
        <table border="1" cellspacing="0" class="col-md-6" style="left: 30%">
            <thead>
                <tr>
                    <th style="width: 300px">Image</th>
                    <th>ProductID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>CategoryID</th>
                    <th>ImportDate</th>
                    <th>UsingDate</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="list" value="${sessionScope.PRODUCTS}"></c:set>
                <c:if test="${not empty list}">
                    <c:forEach var="x" items="${list}">
                        <tr>
                            <td><img src="images/${x.getImage()}" width="30%"></td>
                            <td>${x.getProductID()}</td>
                            <td>${x.getProductName()}</td>
                            <td>${x.getPrice()}</td>
                            <td>${x.getQuantity()}</td>
                            <td>${x.getCategoryID()}</td>
                            <td>${x.getImportDate()}</td>    
                            <td>${x.getUsingDate()}</td>
                            <td><a href="editProduct.jsp?productID=${x.getProductID()}" class="btn btn-outline-success">Edit</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
            <tfoot>
            <th colspan="7"><a href="addProduct.jsp" class="btn btn-outline-primary btn-block btn-lg">Add Product</a></th>
        </tfoot>
    </table>
</body>
</html>
