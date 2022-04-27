<%-- 
    Document   : addProduct
    Created on : Mar 12, 2022, 9:09:35 AM
    Author     : PhatPH
--%>

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
        <title>Add Product</title>
    </head>
    <body>
        <c:set var="name" value="${param.productName}"/>
        <c:set var="price" value="${param.price}"/>
        <c:set var="quantity" value="${param.quantity}"/>
        <c:set var="image" value="${param.image}"/>
        <c:set var="categoryID" value="${param.categoryID}"/>
        <c:set var="importDate" value="${param.importDate}"/>
        <c:set var="usingDate" value="${param.usingDate}"/>

        <h2>ADD PRODUCT</h2>
        <form action="MainController" method="post">
            <p>Name: <input type="text" name="productName" required></p>
            <p>Price: <input type="number" name="price" min="0" max="100000" required></p>
            <p>Quantity: <input type="number" name="quantity" min="1" max="10" required></p>
            <p>CategoryID: <input type="text" name="categoryID" required></p>
            <p>ImportDate: <input type="text" name="importDate" required></p>
            <p>UsingDate <input type="text" name="usingDate" required></p>
            <p><input type="file" name="image" required></p>  
            <input type="submit" class="btn btn-outline-success" name="action" value="Add Product">
        </form>
    </body>
</html>
