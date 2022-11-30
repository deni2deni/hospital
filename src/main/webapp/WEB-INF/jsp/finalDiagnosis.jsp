<%@include file="common/header.jsp" %>
Select final diagnosis for patient:
<form action="<c:url value="/discharge"/>">
    <select name="diagnosisName" required>
        <c:forEach items="${diagnosis}" var="diagnosis">
            <option value="${diagnosis.name}">${diagnosis.name}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="patientsUsername" value="${patientsUsername}">
    <input type="submit" value="Confirm discharge">
</form>
<%@include file="common/footer.jsp" %>