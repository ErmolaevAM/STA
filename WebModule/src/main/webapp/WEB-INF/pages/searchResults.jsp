<%@ taglib prefix="cicl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2016
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search resutls</title>
</head>
<body>
    <div>
        <h1>Search results:</h1>
        <a href="<c:url value="/logout" />"><button>Logout</button></a>
    </div>
    <br>
    <div>
        <table align="center" border="2" cellpadding="2" width="1500">
            <tr>
                <th width="45">ID</th>
                <th width="65">App name</th>
                <th width="45">Connected error</th>
                <th width="1300">Stacktrace</th>
                <th width="45">Ranked coeff</th>
            </tr>

            <cicl:forEach var="searchResults" items="${searchResults}">
                <tbody>
                <td>${searchResults.appId}</td>
                <td>${searchResults.appName}</td>
                <td>${searchResults.stackTraceId}</td>
                <td>
                    <cicl:forEach var="stackTrace" items="${searchResults.stackTraces}">
                        ${stackTrace}<hr/>
                    </cicl:forEach>
                </td>
                <td>${searchResults.rankedCoeff}</td>
                </tbody>
            </cicl:forEach>
        </table>
    </div>
</body>
</html>
