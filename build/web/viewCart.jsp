<%-- 
    Document   : viewCart
    Created on : Mar 8, 2022, 1:56:18 AM
    Author     : PhatPH
--%>

<%@page import="DTO.ProductDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <form id="main" action="MainController" method="post">
            <header style="background-color: #f1f1f1;padding: 20px 10px;">
                <div class="container">
                    <div class="row"> 
                        <div class="col-md-4">
                            <h2><i class="fa fa-shopping-basket" aria-hidden="true"></i>Clean Vegetables</h2><!-- Site name and icon-->
                        </div>                       
                    </div>
                </div>
            </header>
        </form>
        <div style="display: flex; align-items: center; align-content: center; justify-content: center">
            <table border="1" cellspacing="0" style="text-align: center;">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product's Name</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Sub total</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="count" value="0"></c:set>
                    <c:set var="total" value="0"></c:set>
                    <c:if test="${not empty sessionScope.CART}">
                        <c:forEach var="entry" items="${sessionScope.CART}">
                            <c:forEach var="product" items="${sessionScope.PRODUCTS}">
                                <c:if test="${entry.key.getProductID() eq product.getProductID()}">                                   
                                <form action="UpdateCartController?productID=${product.getProductID()}" method="post">
                                    <tr>  
                                        <td>${product.getProductID()}</td>
                                        <td>${product.getProductName()}</td>
                                        <td><img src="images/${product.getImage()}" style="height: 100px; width: 100px"></td>
                                        <td>${product.getPrice()}</td>
                                        <td><input type="number" value="${entry.value}" name="Quantity" style="width: 40px" min="0" max="10"></td>
                                        <td>${product.getPrice() * entry.value}</td>
                                        <c:set var="total" value="${total + product.getPrice()*entry.value}"></c:set>
                                            <td>
                                                <input type="submit" name="action" value="Update" style="width: 100px"></br>
                                                <input type="submit" name="action" value="Remove" style="width: 100px">
                                            </td>
                                        </tr>
                                    </form>
                            </c:if>
                        </c:forEach>
                        <c:set var="count" value="${count + entry.value}"></c:set>
                    </c:forEach>
                </c:if>

                </tbody>
                <tfoot>
                    <tr>
                        <th id="Total" colspan="6" style="text-align: left">Total :</th>
                        <td> ${total} </td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div>
            <form style="padding-top: 10px" action="ProcessOrder" method="post">
                <div>
                    <c:if test="${empty sessionScope.CART}">
                        <p style="text-align: center"> <input type="submit" value="ADD ORDER" class="btn btn-info btn-lg" onclick="emptyCart()"> </p>
                        </c:if>
                        <c:if test="${not empty sessionScope.CART}">
                        <p style="text-align: center"> <input type="submit" value="ADD ORDER" class="btn btn-info btn-lg" name="action"> </p>
                        </c:if>
                </div>
                <input type="hidden" id="count" value="${count}">
                <input type="hidden" id="Total" value="${total}">
            </form>
        </div>
        <div class="col-md-1" style="position: absolute; right: 0; top: 1px;">
            <a href="product.jsp?count=${count}" class="btn btn-info btn-lg">Product</a>
        </div>
        <script>
            function emptyCart() {
                alert("Your cart is empty!");
            }

        </script>
    </body>
</html>
