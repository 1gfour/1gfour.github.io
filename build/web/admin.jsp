<%-- 
    Document   : admin
    Created on : Mar 6, 2022, 7:37:32 PM
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

        <div class="container-fluid" style="font-family: sans-serif; text-align: center;position: absolute; top: 50%; font-size: 60px;">
            <p>Welcome back, ${sessionScope.USERNAME}! </p>
        </div>
        <form action="MainController" method="post">
            <header style="background-color: #f1f1f1;padding: 20px 10px;">
                <div class="container">
                    <div class="row"> 
                        <div class="col-md-4">
                            <h2><i class="fa fa-shopping-basket" aria-hidden="true"></i>Clean Vegetables</h2><!-- Site name and icon-->
                        </div>
                        <div class="col-md-8" style="float: right;">
                            <input type="submit" class="btn btn-warning btn-lg" value="Products" name="action">
                            <input type="submit" class="btn btn-light btn-lg" value="Logout" name="action">
                        </div>
                    </div>
                </div>
            </header>
        </form>
       
</body>
</html>
