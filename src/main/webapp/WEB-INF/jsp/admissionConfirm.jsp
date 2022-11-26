<%@include file="common/header.jsp"%>
    Confirm admission for patients:
    <br>
    <form action="<c:url value="/admissionConfirm"/>" method="post">
        <label>Patient's name:
            <select name="patientsUsername">
            <c:forEach items="${patients}" var="patient">
                <option value="${patient.username}">${patient.name}</option>
            </c:forEach>
            </select>
        </label>
        <label>Choose initial diagnosis:
            <select name="diagnosisId">
                <c:forEach items="${diagnosis}" var="diagnosis">
                    <option value="${diagnosis.id}">${diagnosis.name}</option>
                </c:forEach>
            </select>
        </label>
        <br>
        <input type="submit" value="Confirm admission">
    </form>
<%@include file="common/footer.jsp"%>