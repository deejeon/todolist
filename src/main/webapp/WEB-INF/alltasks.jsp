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
		
		<div class="content">
			<div class="sidebar">
				<div class="selected-tab"><img class="icon" src="img/checklist-icon.png" alt="Checklist Icon" width="20" height="20"><p>All Tasks</p></div>
				<a class="tab" href="/"><img class="icon" src="img/today-icon.png" alt="Checklist Icon" width="20" height="20">Today</a>
				<a class="tab" href="/"><img class="icon" src="img/next-icon.png" alt="Checklist Icon" width="20" height="20">Next 7 Days</a>
				<div class="divider"></div>
				<a class="tab" href="/"><img class="icon" src="img/high-icon.png" alt="Checklist Icon" width="20" height="20">High Priority</a>
				<a class="tab" href="/"><img class="icon" src="img/medium-icon.png" alt="Checklist Icon" width="20" height="20">Medium Priority</a>
				<a class="tab" href="/"><img class="icon" src="img/low-icon.png" alt="Checklist Icon" width="20" height="20">Low Priority</a>
				<div class="divider"></div>
			</div>
			<div class="main-content"></div>
		</div>
	</body>
</html>