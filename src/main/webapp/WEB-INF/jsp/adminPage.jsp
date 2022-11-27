<%@include file="common/header.jsp"%>
Hello Admin!
<br>
Diagnosis control.
<form action="<c:url value="/showDiagnosis"/>">
    <input type="submit" value="Show all diagnosis">
</form>
<br>
Treatment Type control.
<form action="<c:url value="/showTreatmentTypes"/>">
    <input type="submit" value="Show all treatment types">
</form>
<br>
<form action="<c:url value="/addTreatmentType"/>">
    <label>Treatment type name:<input type="text" name="name" placeholder="new treatment type" required></label>
    <label>Treatment type price:<input type="number" name="price" placeholder="1" required></label>
    <input type="submit" value="Add new treatment type">
</form>
<br>
<form action="<c:url value="/registration"/>">
    <input type="submit" value="Add new user">
</form>
<%@include file="common/footer.jsp"%>