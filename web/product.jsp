<%-- 
    Document   : product
    Created on : Mar 6, 2022, 7:36:19 PM
    Author     : PhatPH
--%>

<%@page import="DAO.ProductDAO"%>
<%@page import="DTO.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:url var="logout" value="MainController">
            <c:param name="action" value="Logout"></c:param>
        </c:url>

        <c:set var="count" value="${0}"></c:set> <!--new count = 0-->
        <c:if test="${not empty param.count}">
            <c:set var="count" value="${param.count}"></c:set> <!--count exist, using count in Buy Servlet-->
        </c:if>
        <form action="MainController?count=${count}" method="post">
            <header style="background-color: #f1f1f1; padding: 20px 10px;">
                <div class="container">
                    <div class="row"> 
                        <div class="col-md-4">
                            <h2><i class="fa fa-shopping-basket" aria-hidden="true"></i>Clean Vegetables</h2><!-- Site name and icon-->
                        </div>
                        <div class="col-md-8" style="float: right;">                      
                            <input type="submit" class="btn btn-info btn-lg" value="Product" name="action">
                        </div>
                        <div class="col-md-8" style="float: right;">                      
                            <a class="btn btn-info btn-lg" href="${logout}">Logout</a><br>
                        </div>
                    </div>
                </div>
            </header>
        </form>
        <div class="container-fluid" style="margin: 0;padding: 0;font-family: sans-serif;">
            <div class="row">
                <!--Get list of product-->             
                <c:if test="${not empty sessionScope.PRODUCTS}">
                    <c:forEach var="list" items="${sessionScope.PRODUCTS}">
                        <div class="product col-md-4" style=" border: 2px solid;">
                            <form action="AddToCartController?count=${count}" method="post">
                                <div class="row" style="display: flex; justify-content: center">
                                    <p><input type="hidden" value="${list.getProductID()}" name="productID"></p>
                                    <div class="col-md-6">
                                        <img src="images/${list.getImage()}" style="width: 100%">
                                    </div>
                                    <div class="col-md-6">
                                        <p><b>Product Name:</b> ${list.getProductName()}</p>
                                        <p><b>Quantity:</b> ${list.getQuantity()}</p>
                                        <p><b>Category:</b> ${list.getCategoryID()}</p>
                                        <p><b>Import Date:</b> ${list.getImportDate()}</p>
                                        <p><b>Using Date:</b> ${list.getUsingDate()}</p>
                                        <p><b>Price:</b> ${list.getPrice()}</p>  
                                        <input type="submit" name="action" value="Add To Cart" style=" width: 100%; padding: 5px;font-size: 25px;"class="btn btn-outline-success"></br>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="col-md-1" style="position: absolute; right: 0; top: 0; font-size: 20px">
                    <a href="viewCart.jsp" style="font-size: 30px;"><i class="fa fa-shopping-cart" aria-hidden="true"></i> -${count} </a>
                </div>
            </div>
        </div>
    </body>
</html>
