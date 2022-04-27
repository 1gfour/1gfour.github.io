<%-- 
    Document   : editProduct
    Created on : Mar 11, 2022, 7:38:36 PM
    Author     : PhatPH
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <title>Edit Product</title>
    </head>
    <body>
        <h1>Edit Product</h1>
        <c:set var="list" value="${sessionScope.PRODUCTS}"></c:set>
        <c:set var="proID" value="${param.productID}"></c:set>
        <c:forEach var="x" items="${list}">
            <c:if test="${x.getProductID() eq proID}">
                <form action="MainController" method="post">
                    <p>ID:<input type="text" readonly value="${x.getProductID()}" name="productID" required>
                    <p>Name:<input type="text" value="${x.getProductName()}"name="productName" required></p>
                    <p>Price:<input type="number" value="${x.getPrice()}" name="price" min="0" max="99999999" required> 
                    <p>Quantity:<input type="number" value="${x.getQuantity()}" name="quantity" min="0" max="20" required> 
                    <p>CategoryID:<input type="text" value="${x.getCategoryID()}" name="categoryID" required></p>
                    <p>ImportDate<input type="text" value="${x.getImportDate()}" name="importDate" required></p>
                    <p>UsingDate<input type="text" value="${x.getUsingDate()}" name="usingDate" required></p>
                    <p>Image:<input type="file" name="txtNewPath"></p>
                    <input type="hidden" value="${x.getImage()}" name="txtOldPath">

                    <input type="submit" class="btn btn-outline-success" name="action" value="Update Product">
                    <input type="submit" class="btn btn-outline-success" name="action" value="Remove Product">
                </form>
            </c:if> 
        </c:forEach>
    </body>
</html>
