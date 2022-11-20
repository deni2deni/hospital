<%@include file="common/header.jsp"%>
</head>
<body>
<form method="post" action=<c:url value="/login"/> >
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
<%@include file="common/footer.jsp"%>