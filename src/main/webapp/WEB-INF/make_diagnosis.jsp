<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Make a diagnosis for patient#${patientId}
<form action="" method="post">
    <div>
        <label>Diagnosis:</label>
    </div>
    <div>
        <select size="1" name="diagnosisId">
            <option disabled>Choose diagnosis</option>
            <c:forEach var="diagnosis" items="${diagnoses}">
                <option value="${diagnosis.id}">${diagnosis.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select size="1" name="treatmentId">
            <option disabled>Choose treatment</option>
            <c:forEach var="treatment" items="${treatments}">
                <option value="${treatment.id}">${treatment.name}</option>
            </c:forEach>
        </select>
    </div>
    <input type="hidden" name="patientId" value="${patientId}">
    <input type="submit" value="Confirm diagnosis">
</form>
</body>
</html>