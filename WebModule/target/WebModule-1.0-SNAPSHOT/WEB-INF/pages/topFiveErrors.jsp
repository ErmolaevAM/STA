<%@ taglib prefix="cicl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 17.12.2016
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top five errors</title>
</head>
<body>
    <h1>Top five errors</h1>
    <a href="<c:url value="/logout" />"><button>Logout</button></a>

    <div>
        <table align="center" border="2" cellpadding="2" width="1500">
            <tr>
                <th width="45">ID</th>
                <th width="45">Error</th>
                <th>Stacktrace</th>
            </tr>

            <cicl:forEach var="errors" items="${errors}">
                <tbody>
                <td>${errors.stackTraceId}</td>
                <td>${errors.stackTraceTitle}</td>
                <td>${errors.stackTrace}</td>
                </tbody>
            </cicl:forEach>

        </table>
    </div>
</body>
</html>
