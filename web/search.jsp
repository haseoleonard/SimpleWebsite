<%-- 
    Document   : search
    Created on : Jul 7, 2020, 3:22:01 PM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.FULLNAME}">
            <font style="color: red">Welcome,${sessionScope.FULLNAME}</font>
            <c:url var="logoutLink" value="logout">
                <c:param name="btnAction" value="Logout"/>
            </c:url>
            <a href="${logoutLink}">Logout</a>
            <h2>Search</h2>   
            <form action="search">
                Search value<input type="text" name="txtSearchValue" value="${requestScope.txtSearchValue}"/><br/>
                <input type="submit" name="btnAction" value="Search"/>
            </form>
            <c:set var="SearchValue" value="${requestScope.txtSearchValue}"/>
            <c:if test="${not empty SearchValue}">
                <c:set var="userList" value="${requestScope.SEARCHRESULT}"/>
                <c:if test="${not empty userList}">
                    <c:set var="updateError" value="${requestScope.UPDATEERROR}"/>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>UserName</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Role</th>
                                <th>Update</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${userList}" varStatus="counter">
                                <tr>
                            <form action="update" method="POST">
                                <input type="hidden" name="lastSearchValue" value="${txtSearchValue}"/>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}"/>
                                    <c:if test="${updateError.updatedUsername eq dto.username}">
                                        <c:if test="${not empty updateError.passwordLengthErr}">
                                            <br/><font style="color: red">${updateError.passwordLengthErr} </font>
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtFullname" value="${dto.fullname}"/>
                                    <c:if test="${updateError.updatedUsername eq dto.username}">
                                        <c:if test="${not empty updateError.fullNameLengthErr}">
                                            <br/><font style="color: red">${updateError.fullNameLengthErr} </font>
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.role==true}">
                                               checked="checked"
                                           </c:if>/>
                                </td>
                                <td>
                                    <input type="submit" name="btnAction" value="Update"/>    
                                </td>
                            </form>
                            <form action="delete" method="POST">
                                <td>

                                    <input type="hidden" name="txtUsername" value="${dto.username}"/>
                                    <input type="submit" name="btnAction" value="Delete"/>
                                    <input type="hidden" name="lastSearchValue" value="${txtSearchValue}"/>

                                    <%--<c:url var="deleteLink" value="delete">
                                        <c:param name="btnAction" value="delete"/>
                                        <c:param name="txtUsername" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Remove</a>--%>
                                </td>
                            </form> 
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty userList}">
                <h2>No Record is Match</h2>
            </c:if>   
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.FULLNAME}">
        <h1 style="color: red">You Must Login before using this feature</h1>
        <a href="loginPage">Click Here to login</a>
    </c:if>
</body>
</html>
