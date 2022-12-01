<%@include file="common/header.jsp"%>
Hello Admin!
<br>
Diagnosis control.
<form action="<c:url value="/showDiagnosis"/>">
    <input type="submit" value="Show all diagnosis">
</form>
<br>
<form action="<c:url value="/registration"/>">
    <input type="submit" value="Add new user">
</form>
<%@include file="common/footer.jsp"%>