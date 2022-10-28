<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        #outer {
            width: 100%;
            text-align: left;
        }

        .inner {
            display: inline-block;
        }
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
                <form action="http://localhost:8080/diagnosis">
                    <input type="hidden" name="id" value="${patient.id}">
                    <input type="submit" value="Make a diagnosis">
                </form>
            </div>
            <div class="inner">
                <form action="http://localhost:8080/procedure">
                    <input type="hidden" name="id" value="${patient.id}">
                    <input type="submit" value="Do a procedure">
                </form>
            </div>
            <div class="inner">
                <form action="http://localhost:8080/admission">
                    <input type="hidden" name="id" value="${patient.id}">
                    <input type="submit" value="Do a admission">
                </form>
            </div>
            <div class="inner">
                <form action="http://localhost:8080/history">
                    <input type="hidden" name="id" value="${patient.id}">
                    <input type="submit" value="Show patients history">
                </form>
            </div>
        </div>
    </li>
</c:forEach>
</body>
</html>