<%-- 
    Document   : CreateNewAccount
    Created on : Jul 13, 2020, 2:12:28 AM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
        <form action="createAccount" method="POST">
            Username*<input type="text" name="txtUsername" value="${requestScope.txtUsername}"/></br>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font style="color: red">${errors.usernameLengthErr}</font></br>
            </c:if>
            <c:if test="${not empty errors.usernameContainsWrongCharsErr}">
                <font style="color: red">${errors.usernameContainsWrongCharsErr}</font></br>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font style="color: red">${errors.usernameIsExisted}</font></br>
            </c:if>
            Password*<input type="password" name="txtPassword" value=""/></br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font style="color: red">${errors.passwordLengthErr}</font></br>
            </c:if>
            <c:if test="${not empty errors.passwordContainsWrongCharsErr}">
                <font style="color: red">${errors.passwordContainsWrongCharsErr}</font></br>
            </c:if>
            Confirm*<input type="password" name="txtConfirm" value=""/></br>
            <c:if test="${not empty errors.confirmNotMatchedErr}">
                <font style="color: red">${errors.confirmNotMatchedErr}</font></br>
            </c:if>
            Full Name*<input type="text" name="txtFullName" value="${requestScope.txtFullName}"/></br>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font style="color: red">${errors.fullNameLengthErr}</font></br>
            </c:if>
            <input type="submit" name="btnAction" value="Create New Account">
            <input type="reset" name="btnReset" value="Reset"/>
        </form>
    </body>
</html>
