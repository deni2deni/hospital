<%@include file="common/header.jsp"%>
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
<%@include file="common/backToPatients.jsp"%>
<%@include file="common/footer.jsp"%>