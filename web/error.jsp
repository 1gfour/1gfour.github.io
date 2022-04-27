<%-- 
    Document   : error
    Created on : Mar 6, 2022, 5:17:42 AM
    Author     : PhatPH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="color: red">ERROR: <%= session.getAttribute("ERROR_MESSAGE")%></h1>
    </body>
</html>
