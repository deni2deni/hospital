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
<table>
    <tr>
        <th>Date</th>
        <th>Patient</th>
        <th>Doctor</th>
        <th>Diagnosis</th>
        <th>Treatment Doctor</th>
        <th>Treatment</th>
        <th>Treatment Status</th>
    </tr>
    <c:forEach var="journal" items="${journal}">
        <tr>
            <th><c:out value="${journal.date}"/></th>
            <th><c:out value="${journal.patient.name}"/></th>
            <th><c:out value="${journal.doctor.name}"/></th>
            <th><c:out value="${journal.diagnosis.name}"/></th>
            <th><c:out value="${journal.treatmentDoctor.name}"/></th>
            <th><c:out value="${journal.treatment.name}"/></th>
            <th><c:out value="${journal.treatmentStatus}"/></th>
            <th><c:if test="${journal.treatmentStatus == 'SCHEDULED'}">
                <div>
                    <form action="/procedure">
                        <input type="hidden" name="id" value="${journal.id}">
                        <input type="submit" value="Do a procedure">
                    </form>
                </div>
            </c:if>
            </th>
        </tr>
    </c:forEach>
</table>
<div class="inner">
    <form action="/admission">
        <input type="hidden" name="id" value="${patientId}">
        <input type="submit" value="Do a admission">
    </form>
</div>
<div class="inner">
    <form action="/patient">
        <input type="hidden" name="id" value="${patientId}">
        <input type="submit" value="Show patients page">
    </form>
</div>
</body>
</html>