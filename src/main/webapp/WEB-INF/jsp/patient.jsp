<%@include file="common/header.jsp"%>
    Welcome  ${user.name} <br>
    Your diagnosis is: <br>
    Your bill sum is:  ${sum} <br>

    <form action="<c:url value="/history"/>">
        <input type="hidden" value="${user.id}" name="id">
        <input type="submit" value="Show treatment history">
    </form>

    <form action="<c:url value="/addcard"/>">
        <input type="hidden" value="${user.id}" name="id">
        <input type="submit" value="Add credit card">
    </form>

    <form action="<c:url value="/bill"/>">
        <input type="submit" value="Pay for bills">
    </form>
<%@include file="common/footer.jsp"%>