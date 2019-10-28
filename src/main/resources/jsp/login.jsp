<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        Login
    </div>
    <form method="post" action="/login">
        <input type="text" name="name"> <br>
        <input type="text" name="password"><br>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>