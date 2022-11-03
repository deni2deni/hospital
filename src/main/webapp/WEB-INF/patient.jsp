<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Welcome  ${user.name} <br>
    Your diagnosis is: ${user.finalDiagnosis.name} <br>
    Your bill sum is:  ${sum} <br>

    <form action="/history">
        <input type="submit" value="Show treatment history">
    </form>

    <form action="/addcard">
        <input type="submit" value="Add credit card">
    </form>

    <form action="/bill">
        <input type="submit" value="Pay for bills">
    </form>

</body>
</html>