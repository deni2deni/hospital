<%@include file="common/header.jsp"%>
        <label>Filter by status:</label>
        <form method="post" action="<c:url value="/patients/filter"/>">
            <select name="filter">
                <c:forEach var="status" items="${status}">
                    <option value="${status.name()}">${status.name()}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Apply filter">
        </form>
        <form action="<c:url value="/patients"/>">
            <input type="submit" value="Reset filter">
        </form>
        <br>
        Patients:
        <c:forEach var="patient" items="${patients}">
            <li>
                <c:out value="${patient.id}"/>
                <c:out value="${patient.name}"/>
                Status is : <c:out value="${patient.status}"/>
                <div id="outer">
                    <div class="inner">
                        <form action="<c:url value="/diagnosis"/>">
                            <input type="hidden" name="id" value="${patient.id}">
                            <input type="submit" value="Make a diagnosis">
                        </form>
                    </div>

                    <div class="inner">
                        <form action="<c:url value="/admission"/>">
                            <input type="hidden" name="id" value="${patient.id}">
                            <input type="submit" onclick="return confirm('Are you sure?')" value="Do a admission">
                        </form>
                    </div>
                    <div class="inner">
                        <form action="<c:url value="/history"/>">
                            <input type="hidden" name="id" value="${patient.id}">
                            <input type="submit" value="Show patients history">
                        </form>
                    </div>
                </div>
            </li>
        </c:forEach>
<%@include file="common/footer.jsp"%>