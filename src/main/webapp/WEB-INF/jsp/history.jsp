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
               <sec:authorize access="hasAnyRole('DOCTOR','NURSE')">
                   <c:if test="${journal.treatmentDto.treatmentStatus == 'SCHEDULED'}">
                       <c:choose>
                           <c:when test="${journal.treatmentDto.treatmentTypeDto.name != 'surgeon'}">
                               <th>
                                   <div>
                                        <form action="<c:url value="/procedure"/>">
                                            <input type="hidden" name="id" value="${journal.id}">
                                            <input type="submit" value="Do a procedure">
                                        </form>
                                   </div>
                               </th>
                           </c:when>
                           <c:otherwise>
                               <sec:authorize access="hasRole('DOCTOR')">
                                   <th>
                                       <div>
                                           <form action="<c:url value="/procedure"/>">
                                               <input type="hidden" name="id" value="${journal.id}">
                                               <input type="submit" value="Do a procedure">
                                           </form>
                                       </div>
                                   </th>
                               </sec:authorize>
                           </c:otherwise>
                       </c:choose>
                   </c:if>
               </sec:authorize>
        </tr>
    </c:forEach>
</table>
<sec:authorize access="hasAnyRole('DOCTOR','NURSE')">
    <%@include file="common/backToPatients.jsp"%>
</sec:authorize>
<%@include file="common/footer.jsp"%>