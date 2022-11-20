<%@include file="common/header.jsp"%>
    ID NAME ROLE
    <c:forEach var="user" items="${users}">
        <li>
            <c:out value="${user.id}"/>
            <c:out value="${user.name}"/>
            <c:out value="${user.roleDto.name}"/>
        </li>
    </c:forEach>
<%@include file="common/footer.jsp"%>