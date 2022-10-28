<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ID NAME ROLE
    <c:forEach var="user" items="${users}">
        <li>
            <c:out value="${user.id}"/>
            <c:out value="${user.name}"/>
            <c:out value="${user.role.name}"/>
        </li>
    </c:forEach>
</body>
</html>