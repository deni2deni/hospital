<%@include file="common/header.jsp"%>
    Welcome  ${user.name} <br>
    Your bill sum is:  ${sum} <br>
    <c:if test="${user.status == 'NEW' }">
        <form action="<c:url value="/moveToHospital"/> ">
            Currently, you aren't in hospital.
            <br>
            <input type="submit" value="Move to hospital.">
        </form>
        <br>
    </c:if>
    <c:if test="${user.status == 'WAITING_FOR_ADMISSION' }">
        Waiting for doctor.
        You can cancel admission.
        <form action="<c:url value="/cancelAdmission"/> ">
            <input type="submit" value="Cancel Admission">
        </form>
        <br>
    </c:if>

    <form action="<c:url value="/myHistory"/>">
        <input type="submit" value="Show treatment history">
    </form>

    Billing info.
    <br>
    <c:if test="${hasNoCard}">
        Please add payment card to pay for bills.
    </c:if>
        <form action="<c:url value="/addCard"/>">
            <input type="submit" value="Add credit card">
        </form>
    <c:if test="${!hasNoCard && sum > 0}">
        <form action="<c:url value="/bill"/>">
            <input type="submit" value="Pay for bills">
        </form>
    </c:if>
</body>
</html>