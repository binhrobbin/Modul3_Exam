<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css">
</head>
<body>
<%--<jsp:include page="header.jsp"/>--%>
<h1 align="center">Student list</h1>
<a id="" class="btn btn-primary" href="/student?action=create" role="button">Create</a>
<form class="btn btn-light" action="/student?action=search" method="post" style="margin-left: 70%">
    <input type="text"  name="nameSearch" value="${nameSearch}" >
    <button type="submit" >Search</button>
</form><br>
<c:if test='${message != null}'>
    <span class="message" style="text-align: center;color: red">${message}</span>
</c:if>
<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>DateOfBirth</th>
        <th>Email</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Classroom</th>
        <th>Action</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="student">
    <tr>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.dateOfBirth}</td>
        <td>${student.email}</td>
        <td>${student.address}</td>
        <td>${student.phoneNumber}</td>
        <td>${student.classroom}</td>
        <td><a  class="btn btn-warning" href="/student?action=edit&id=${student.id}" role="button">Edit</a></td>
        <td><a  class="btn btn-danger" href="/student?action=delete&id=${student.id}" role="button">Delete</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
