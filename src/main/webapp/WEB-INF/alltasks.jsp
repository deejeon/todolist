<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>All Tasks | Todolist</title>
		<link rel="stylesheet" href="/css/alltasks.css">
	</head>
	<body>
		<div class="header">
			<h1>Todolist</h1>
			<div class="user-info">
				<p>Logged in as <c:out value="${currentUser.firstName}"/><p>
				<a href="/logout">Logout</a>
			</div>
		</div>
	</body>
</html>