
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="/admin/UsersList">Users list</a>
    <div>
        Hi, it's users page.
    </div>

    <input type="button" value="Logout"
           onclick="window.location.href='/logout'; return false;"/>
</div>
</body>
</html>