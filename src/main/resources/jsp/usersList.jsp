<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

You can add user
<div>
    <a href="/admin/add">Add user</a>
</div>

<div>
    <div>
        List of users
    </div>
    <table>
        <tr>
            <th>User id</th>
            <th>User name</th>
            <th>Password</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${usersFromServer}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td><c:forEach var="role" items="${user.roles}">${role.role}<br> </c:forEach></td>
                    <%--  <td>
                        <c:forEach var="role" items="${user.roles}">${role.role}<br> </c:forEach>
                    </td>  --%>

                <td>
                    <a href="/admin/edit?id=<c:out value='${user.id}' />">Edit</a>
                </td>
                <td>
                    <a href="/admin/delete?id=<c:out value='${user.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <input type="button" value="Logout"
           onclick="window.location.href='/logout'; return false;"/>
</div>
</body>
</html>