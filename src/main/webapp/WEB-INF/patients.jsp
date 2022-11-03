<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="styles.css"%>
    </style>
</head>
<body>
Patients:
<c:forEach var="patient" items="${patients}">
    <li>
        <c:out value="${patient.id}"/>
        <c:out value="${patient.name}"/>
        <div id="outer">
            <div class="inner">
                <form action="/diagnosis">
                    <input type="hidden" name="id" value="${patient.id}">
                    <input type="submit" value="Make a diagnosis">
                </form>
            </div>

            <div class="inner">
                <form action="/admission">
                    <input type="hidden" name="id" value="${patient.id}">
                    <input type="submit" onclick="return confirm('Are you sure?')" value="Do a admission">
                </form>
            </div>
            <div class="inner">
                <form action="/history">
                    <input type="hidden" name="id" value="${patient.id}">
                    <input type="submit" value="Show patients history">
                </form>
            </div>
        </div>
    </li>
</c:forEach>
</body>
</html>