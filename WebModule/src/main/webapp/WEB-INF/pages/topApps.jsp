<%@ taglib prefix="cicl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 17.12.2016
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top broken applications</title>
</head>
<body>
    <div>
        <h1>Top broken applications</h1>
        <a href="<c:url value="/logout" />"><button>Logout</button></a>
    </div>
    <br>
    <div>
        <table align="center" border="2" cellpadding="2" width="1500">
            <tr>
                <th width="45">ID</th>
                <th width="45">App name</th>
                <th width="45">Connected error</th>
                <th width="45">Ranked coeff</th>
            </tr>

            <cicl:forEach var="brokenApps" items="${brokenApps}">
                <tbody>
                <td>${brokenApps.appId}</td>
                <td>${brokenApps.appName}</td>
                <td>${brokenApps.stackTraceId}</td>
                <td>${brokenApps.rankedCoeff}</td>
                </tbody>
            </cicl:forEach>

        </table>
    </div>
</body>
</html>
