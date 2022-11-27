<%@include file="common/header.jsp"%>
Registration:
<form method="post" action="<c:url value="/registration"/> ">
    <label>Username:</label><input type="text" name="username" required>
    <label>Password:</label><input type="password" name="password" required>
    <br>
    <label>Name:</label><input type="text" name="name" required>
    <sec:authorize access="hasRole('ADMIN')">
        <label>Role:</label>
        <select name="role">
            <c:forEach items="${roles}" var="role">
                <option value="${role}">${role.name()}</option>
            </c:forEach>
        </select>
    </sec:authorize>
    <br>
    <input type="submit" value="Register">
</form>
<%@include file="common/footer.jsp"%>