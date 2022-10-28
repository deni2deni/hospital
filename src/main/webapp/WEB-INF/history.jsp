<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
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
            <th><c:out value="${journal.treatment_status}"/></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>