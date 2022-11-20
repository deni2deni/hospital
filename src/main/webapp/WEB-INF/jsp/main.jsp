<%@include file="common/header.jsp"%>
<form action="" method="post">
    <div>
        <label>Username:</label>
        <input type='text' required name="name"/>
    </div>
    <div>
        <select size="1" name="role">
            <option disabled>Choose role</option>
            <option value="1">patient</option>
            <option value="2">doctor</option>
            <option value="3">nurse</option>
        </select>
    </div>
    <input type="submit" value="Confirm registration">
</form>

<form action="<c:url value="/users"/>">
    <input type="submit" value="Show all users">
</form>

<form action="<c:url value="/patients"/>">
    <input type="submit" value="Show all patients">
</form>

</body>
</html>