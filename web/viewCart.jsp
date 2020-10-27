<%-- 
    Document   : viewCart
    Created on : Jul 13, 2020, 11:26:25 AM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Your Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="removeProductFromCart" method="GET">
                    <table border="1">
                        <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Product Name</th>
                                    <th>Quantity</th>
                                    <th>Select</th>
                                    
                                </tr>
                            </thead>
                            <tbody>                  
                    <c:forEach var="item" items="${items}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.key}</td>
                                    <td>${item.value}</td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.key}"/>
                                    </td>
                                </tr>                            
                    </c:forEach>
                                <tr>
                                    <td colspan="3">
                                        <a href="loadProduct">Add More Book To Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" name="btnAction" value="Remove"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                </form>
                <a href="checkOutPage">Click Here to check out</a>
            </c:if>
            <c:if test="${empty items}">
                <h1>No Items In Cart!!!</h1>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h1>No Cart Existed!!!</h1>
        </c:if>
    </body>
</html>
