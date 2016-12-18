<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.11.2016
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome page</h1>

    <div>
        <a href="<c:url value="/logout" />"><button>Logout</button></a>

        <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
            <td><a href="<c:url value="/topFiveApps"/>"><button onclick="">Get top five broken applications</button></a></td>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
            <td><a href="<c:url value="/topFiveErrors"/>"><button>Get top five errors</button></a></td>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td><a href="<c:url value="/topApps"/>"><button>Get all broken applications</button></a></td>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td><a href="<c:url value="/topErrors"/>"><button>Get all errors</button></a></td>
        </sec:authorize>

    </div>
    <br>
    <form name='f' action="/searchResults" method="post">
        <input name="searchSubstring" id="searchSubstring" type="text" size="50" placeholder="Enter part of stacktrace"/>
        <%--<td><a href="<c:url value="/searchResults"/>"><button>Search</button></a></td>--%>
        <input type="submit" value="Search"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>

</body>
</html>
