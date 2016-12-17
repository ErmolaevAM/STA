<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.11.2016
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>STA: login page</title>
</head>
<body>
    <h1>StackTrace analyser: login page</h1>

    <div>
        <h2>Enter your login and password</h2>
        <c:url var="loginUrl" value="/j_spring_security_check"/>
        <form name='f' action="${loginUrl}" method="post">
            <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                    <td>Your login:   </td>
                    <td><input type='text' name='j_username' value=''></td>
                </tr>
                <tr>
                    <td>Your password:</td>
                    <td><input type='password' name='j_password' /></td>
                </tr>

                <tr>
                    <td colspan='2'>
                        <input name="Enter" type="submit" value="Enter" />
                    </td>
                </tr>

            </table>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

        </form>
    </div>

</body>
</html>
