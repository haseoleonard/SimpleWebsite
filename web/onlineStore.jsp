<%-- 
    Document   : onlineStore
    Created on : Jul 13, 2020, 5:02:30 AM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Store</title>
    </head>
    <body>
        <h1>Online Book Store</h1>
        <h2>Select Item to Add to your cart</h2>
        <c:set var="productList" value="${requestScope.PRODUCTLIST}"/>
        <c:if test="${not empty productList}">
            <form action="midOrder" method="GET">
                Choose Product<select name="cboProduct">
                    <c:forEach var="dto" items="${productList}" varStatus="counter">
                        <option 
                            <c:if test="${not empty param.lastSelectedProduct}">
                                <c:if test="${dto.productName eq param.lastSelectedProduct}">
                                    selected="true"
                                </c:if>
                            </c:if>>
                            ${dto.productName}
                        </option>
                    </c:forEach>
                </select></br>
                <input type="submit" name="btnAction" value="Add To Cart"/>
                <input type="submit" name="btnAction" value="View Your Cart"/>
            </form>
        </c:if>
    </body>
</html>
