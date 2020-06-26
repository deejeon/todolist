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
				<div class="selected-tab"><img class="icon" src="/img/checklist-icon.png" width="20" height="20"><p>All Tasks <span class="size"><c:out value="${createdTasks.size() + assignedTasks.size()}"/></span></p></div>
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
				<h1>All Tasks</h1>
				<c:forEach items="${createdTasks}" var="createdTask">
				<div class="task-div">
					<p class="task-div-task"><img class="icon" src="/img/${createdTask.category.name}-icon.png" width="20" height="20"><c:out value="${createdTask.title}"/><span>(Due: <c:out value="${createdTask.deadline}"/>)</span></p>
					<p class="task-div-assignee">Assigned to <span><c:out value="${createdTask.assignee.firstName}"/> <c:out value="${createdTask.assignee.lastName}"/></span></p>
				</div>
				<div class="task-divider"></div>
				</c:forEach>
				<div class="task-div">
					<p><a class="task-div-task" href="/tasks/new"><img class="icon" src="/img/plus-icon.png" width="20" height="20">New Task</a></p>
				</div>
				
				<h1 class="assigned-header">Assigned to Me By Others</h1>
				<c:forEach items="${assignedTasks}" var="task">
				<div class="task-div">
					<p class="task-div-task"><img class="icon" src="/img/${task.category.name}-icon.png" width="20" height="20"><c:out value="${task.title}"/><span>(Due: <c:out value="${task.deadline}"/>)</span></p>
					<p class="task-div-assignee">Created by <span><c:out value="${task.creator.firstName}"/> <c:out value="${task.creator.lastName}"/></span></p>
				</div>
				<div class="task-divider"></div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>