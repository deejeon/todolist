<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Task | Todolist</title>
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
				<p>Logged in as <c:out value="${currentUser.firstName}"/><p>
				<a href="/logout">Logout</a>
			</div>
		</div>
		
		<div class="content">
			<div class="sidebar">
				<div class="selected-tab"><img class="icon" src="/img/plus-icon.png" width="20" height="20"><p>New Task</p></div>
				<div class="divider"></div>
				<a class="tab" href="/tasks"><img class="icon" src="/img/checklist-icon.png" width="20" height="20">All Tasks <span class="size"><c:out value="${createdTasks.size() + assignedTasks.size()}"/></span></a>
				<a class="tab" href="/"><img class="icon" src="/img/today-icon.png" width="20" height="20">Today</a>
				<a class="tab" href="/"><img class="icon" src="/img/next-icon.png" width="20" height="20">Next 7 Days</a>
				<div class="divider"></div>
				<a class="tab" href="/tasks/high-priority"><img class="icon" src="/img/high-icon.png" width="20" height="20">High Priority <span class="size"><c:out value="${highTasks.size() + highAssignedTasks.size()}"/></span></a>
				<a class="tab" href="/tasks/medium-priority"><img class="icon" src="/img/medium-icon.png" width="20" height="20">Medium Priority <span class="size"><c:out value="${mediumTasks.size() + mediumAssignedTasks.size()}"/></span></a>
				<a class="tab" href="/tasks/low-priority"><img class="icon" src="/img/low-icon.png" width="20" height="20">Low Priority <span class="size"><c:out value="${lowTasks.size() + lowAssignedTasks.size()}"/></span></a>
				<div class="divider"></div>
				<c:forEach items="${allCategories}" var="cat">
				<a class="tab" href="/"><img class="icon" src="/img/${cat.name}-icon.png" width="20" height="20"><c:out value="${cat.name}"/></a>
				</c:forEach>
			</div>
			<div class="main-content">
				<h1>New Task</h1>
				<form:form class="form" action="/tasks" method="POST" modelAttribute="task">
					<div class="form-div">
			            <form:label path="title">Task Title </form:label>
			            <form:errors class="errormessage" path="title"/>
			            <form:input class="input-box" path="title"/>
			        </div>
			        <div class="form-div">
			            <form:label path="category.id">Category</form:label>
			            <form:errors class="errormessage" path="category.id"/>
			            <form:select class="input-box input-box-select" path="category.id" required="true">
			            	<option value="" disabled selected>Select a Category</option>
			            	<c:forEach items="${allCategories}" var="cat">
			            	<form:option value="${cat.id}"><c:out value="${cat.name}"/></form:option>
			            	</c:forEach>
			            </form:select>
			        </div>
			        <div class="form-div">
			            <form:label path="deadline">Due Date</form:label>
			            <form:errors class="errormessage" path="deadline"/>
			            <form:input type="date" class="input-box" path="deadline"/>
			        </div>
			        <div class="form-div">
			            <form:label path="priority">Priority Level</form:label>
			            <form:errors class="errormessage" path="priority"/>
			            <form:select class="input-box input-box-select" path="priority" required="true">
			            	<option value="" disabled selected>Select a Priority Level</option>
			            	<form:option value="high">High</form:option>
			            	<form:option value="medium">Medium</form:option>
			            	<form:option value="low">Low</form:option>
			            </form:select>
			        </div>
			        <div class="form-div">
			            <form:label path="assignee.id">Assign to</form:label>
			            <form:errors class="errormessage" path="assignee.id"/>
			            <form:select class="input-box input-box-select" path="assignee.id" required="true">
			            	<option value="" disabled selected>Select an Assignee</option>
			            	<c:forEach items="${allUsers}" var="u">
			            	<form:option value="${u.id}"><c:out value="${u.firstName}"/> <c:out value="${u.lastName}"/></form:option>
			            	</c:forEach>
			            </form:select>
			            <input class="btn" type="submit" value="Create"/>
			        </div>
				</form:form>
			</div>
		</div>
	</body>
</html>