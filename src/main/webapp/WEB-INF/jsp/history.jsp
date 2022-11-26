<%@include file="common/header.jsp"%>
<table>
    <tr>
        <th>Date</th>
        <th>Procedure type</th>
        <th>Patient</th>
        <th>Doctor</th>
        <th>Diagnosis</th>
        <th>Treatment</th>
        <th>Treatment Status</th>
        <th></th>
    </tr>
    <c:forEach var="journal" items="${journal}">
        <tr>
            <th><c:out value="${formatter.format(journal.date)}"/></th>
            <th><c:out value="${journal.treatmentStatus}"/></th>
            <th><c:out value="${journal.patientDto.name}"/></th>
            <th><c:out value="${journal.doctorDto.name}"/></th>
            <th><c:out value="${journal.diagnosisDto.name}"/></th>
            <th><c:out value="${journal.treatmentDto.treatmentTypeDto.name}"/></th>
            <th><c:out value="${journal.treatmentDto.treatmentStatus}"/></th>
            <th><c:if test="${journal.treatmentDto.treatmentStatus == 'SCHEDULED'}">
               <sec:authorize access="hasAnyRole('DOCTOR','NURSE')">
                   <div>
                        <form action="<c:url value="/procedure"/>">
                            <input type="hidden" name="id" value="${journal.id}">
                            <input type="submit" value="Do a procedure">
                        </form>
                    </div>
               </sec:authorize>
            </c:if>
            </th>
        </tr>
    </c:forEach>
</table>
<sec:authorize access="hasAnyRole('DOCTOR','NURSE')">
    <%@include file="common/backToPatients.jsp"%>
</sec:authorize>
<%@include file="common/footer.jsp"%>