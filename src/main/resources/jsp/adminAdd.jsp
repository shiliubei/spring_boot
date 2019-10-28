<%@ page import="java.util.ArrayList" %>
<%@ page import="app.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="/admin/userlist">Users list</a>
    <div>
        Add user
    </div>
    <form method="post" action="/admin/add">
        <input type="text" name="name"> <br>
        <input type="text" name="password"><br>
        <th>User Role : </th>

        <td>
        <input  type="checkbox" name="role" value="ROLE_ADMIN" > Admin
        <input  type="checkbox" name="role" value="ROLE_USER"> User
        </td>

        <input type="submit" value="Add user">
    </form>
</div>
</body>
</html>