<%-- 
    Document   : checkOutPage
    Created on : Jul 14, 2020, 2:05:02 PM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out</title>
    </head>
    <body>
        <h1>Check Out</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:set var="error" value="${requestScope.CHECKOUTERROR}"/>
            <c:if test="${not empty items}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.key}</td>
                                <td>${item.value}</td>
                            </tr>    
                        </c:forEach>                        
                    </tbody>
                </table>
                <form action="checkOut">
                    <br/>
                    Customer Name*<input type="text" name="txtCustomerName" value="${requestScope.txtCustomerName}"/><br/>
                    <c:if test="${not empty error.customerNameEmptyErr}">
                        <font style="color: red">${error.customerNameEmptyErr}</font><br/>
                    </c:if>
                    Customer Address*<input type="text" name="txtCustomerAddress" value="${requestScope.txtCustomerAddress}"/><br/>
                    <c:if test="${not empty error.customerAddressEmptyErr}">
                        <font style="color: red">${error.customerAddressEmptyErr}</font><br/>
                    </c:if>
                    <input type="submit" name="btnAction" value="CheckOut"/>
                </form>
            </c:if>
            <c:if test="${empty items}">
                <h1>No Item in Cart!!!! Please add product to cart before checkout</h1>
                <a href="loadProduct">Click Here to add product to cart</a>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h1>Cart not existed!!!! Please add product to cart before checkout</h1>
            <a href="loadProduct">Click Here to add product to cart</a>
        </c:if>
    </body>
</html>
