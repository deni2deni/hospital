<%@include file="common/header.jsp"%>
<c:forEach var="diagnosis" items="${diagnosis}">
    ${diagnosis.name}
    <br>
</c:forEach>
<br>
<form method="post" action="<c:url value="/addDiagnosis"/>">
    <label>Diagnosis name:<input type="text" name="name" placeholder="new diagnosis" required></label>
    <input type="submit" value="Add new diagnosis">
</form>
<%@include file="common/footer.jsp"%>