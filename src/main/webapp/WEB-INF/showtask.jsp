<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Task Details | Todolist</title>
		<link rel="stylesheet" href="/css/alltasks.css">
		<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.png">
	</head>
	<body>
		<div class="header">
			<div class="logo">
				<img class="main-logo" src="/img/header-logo-red.png" width="35" height="35">
				<h1>Todolist</h1>
			</div>
			<div class="user-info">
				<p>Logged in as <span><c:out value="${currentUser.firstName}"/></span><p>
				<a href="/logout">Logout</a>
			</div>
		</div>
		
		<div class="content">
			<div class="sidebar">
				<a class="tab" href="/tasks/new"><img class="icon" src="/img/plus-icon.png" width="20" height="20">New Task</a>
				<div class="divider"></div>
				<a class="tab" href="/tasks"><img class="icon" src="/img/checklist-icon.png" width="20" height="20">All Tasks <span class="size"><c:out value="${createdTasks.size() + assignedTasks.size()}"/></span></a>
				<a class="tab" href="/"><img class="icon" src="/img/today-icon.png" width="20" height="20">Today</a>
				<a class="tab" href="/"><img class="icon" src="/img/next-icon.png" width="20" height="20">Next 7 Days</a>
				<div class="divider"></div>
				<a class="tab" href="/tasks/high-priority"><img class="icon" src="/img/high-icon.png" width="20" height="20">High Priority <span class="size"><c:out value="${highTasks.size()}"/></span></a>
				<a class="tab" href="/tasks/medium-priority"><img class="icon" src="/img/medium-icon.png" width="20" height="20">Medium Priority <span class="size"><c:out value="${mediumTasks.size()}"/></span></a>
				<a class="tab" href="/tasks/low-priority"><img class="icon" src="/img/low-icon.png" width="20" height="20">Low Priority <span class="size"><c:out value="${lowTasks.size()}"/></span></a>
				<div class="divider"></div>
				<c:forEach items="${allCategories}" var="cat">
				<a class="tab" href="/"><img class="icon" src="/img/${cat.name}-icon.png" width="20" height="20"><c:out value="${cat.name}"/></a>
				</c:forEach>
			</div>
			<div class="main-content">
				<h1><c:out value="${currentTask.title}"/></h1>
				<p class="detail-line">Created by <span class="bold"><c:out value="${currentTask.creator.firstName}"/> <c:out value="${currentTask.creator.lastName}"/></span> | Assigned to <span class="bold"><c:out value="${currentTask.assignee.firstName}"/> <c:out value="${currentTask.assignee.lastName}"/></span></p>
				<p class="detail-line detail-line-with-icon">Category: <span class="detail-line-category"><img class="icon" src="/img/${currentTask.category.name}-icon.png" width="20" height="20"><c:out value="${currentTask.category.name}"/></span></p>
				<p class="detail-line">Deadline: <span class="detail-line-deadline"><c:out value="${formattedDeadline}"/></span></p>
				<p class="detail-line detail-line-with-icon">Priority: <span class="detail-line-priority"><img class="icon" src="/img/${currentTask.priority}-icon.png" width="20" height="20"><c:out value="${currentTask.priority.substring(0, 1).toUpperCase().concat(currentTask.priority.substring(1))}"/></span></p>
				<p class="detail-line detail-line-with-icon">Completed: <span class="detail-line-completed"><img class="icon" src="/img/${currentTask.completed}-icon.png" width="20" height="20"></span></p>
				
				<p class="detail-line">
					<c:if test="${isCreator}">
					<form class="detail-line" action="/tasks/${currentTask.id}" method="POST">
						<input type="hidden" name="_method" value="DELETE">
						<input class="delete-btn" type="submit" value="Delete">
					</form>
					</c:if>
				</p>
			</div>
		</div>

	</body>
</html>