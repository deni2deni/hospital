<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    label {
        display: inline-block;
        width: 100px;
    }
</style>
<form action="" method="post">
    <div>
        <label>Username:</label>
        <input type='text' name="Name" id='username'/>
    </div>
    <div>
        <select size="1" name="roles">
            <option disabled>Choose role</option>
            <option value="1">patient</option>
            <option value="2">doctor</option>
            <option value="3">nurse</option>
        </select>
    </div>
    <input type="submit" value="Confirm registration">
</form>
</body>
</html>