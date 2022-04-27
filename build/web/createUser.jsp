<%-- 
    Document   : createUser
    Created on : Mar 7, 2022, 11:09:29 AM
    Author     : PhatPH
--%>

<%@page import="user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <h1>Create new user!</h1>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" required=""/>
            <%= userError.getUserIDError()%><br>
            Full Name <input type="text" name="userName" required=""/>
            <%= userError.getUserNameError()%><br>
            Role ID <input type="text" name="roleID" required=""/>
            <%= userError.getRoleIDError()%><br>
            Password <input type="password" name="password" required=""/>
            <%= userError.getPasswordError()%><br>
            Confirm<input type="password" name="confirm" required=""/>
            <%= userError.getConfirmError()%><br>
            <input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/>
            <%= userError.getMessageError()%>
        </form>
    </body>
</html>
