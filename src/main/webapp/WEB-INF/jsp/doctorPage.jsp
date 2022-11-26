<%@include file="common/header.jsp"%>
    Welcome  ${user.name} <br>
    <sec:authorize access="hasRole('DOCTOR')">
        <c:if test="${count gt 0}">
            ${count} patient currently waiting for admission! <br>
            <form action="<c:url value="/admissionConfirm"/>">
                <input type="submit" value="Show patients">
            </form>
        </c:if>
    </sec:authorize>
    <form action="<c:url value="/patients"/>">
        <input type="hidden" name="filter" value="ACTIVE">
        <input type="submit" value="Show patients in hospital">
    </form>
</body>
</html>