<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign Up | Todolist</title>
		<link rel="stylesheet" href="/css/register.css">
		<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.png">
	</head>
	<body>
		<main role="main" class="standalone_page">
			<div class="standalone_page__frame">
				<div class="standalone_page__logo">
					<h1><a class="logo" href="/"><img class="main-logo" src="/img/header-logo-white.png" width="30" height="30">Todolist</a></h1>
				</div>
				<div class="standalone_page__content">
					<div id="signup" class="login_signup_form">
						<h1>Sign up</h1>
						<a class="ist_button" href="">
				            <img width="16" height="16" src="https://d3ptyyxy2at9ui.cloudfront.net/google-41de20.svg">
				            Continue with Google
				        </a>
				        <a class="ist_button" href="">
				            <img width="16" height="16" src="https://d3ptyyxy2at9ui.cloudfront.net/facebook-fadd25.svg">
				            Continue with Facebook
				        </a>
				        <div class="separator">
				        	<div class="middle-separator">OR</div>
			        	</div>
			        	<form:form class="form" action="/users" method="POST" modelAttribute="user">
					        <div class="form-div">
					            <%-- <form:label path="lastName">Last Name: </form:label> --%>
					            <form:errors class="errormessage" path="firstName"/>
					            <form:input class="input-box" path="firstName" placeholder="First name"/>
					        </div>
					        <div class="form-div">
					            <%-- <form:label path="lastName">Last Name: </form:label> --%>
					            <form:errors class="errormessage" path="lastName"/>
					            <form:input class="input-box" path="lastName" placeholder="Last name"/>
					        </div>
					        <div class="form-div">
					            <%-- <form:label path="email">Email: </form:label> --%>
					            <form:errors class="errormessage" path="email"/>
					            <form:input class="input-box" type="email" path="email" placeholder="Email address"/>
					        </div>
					        <div class="form-div">
					            <%-- <form:label path="password">Password:</form:label> --%>
					            <form:errors class="errormessage" path="password"/>
					            <form:password class="input-box" path="password" placeholder="Password"/>
					        </div>
					        <div class="form-div">
					            <%-- <form:label path="passwordConfirmation">Password Confirmation:</form:label> --%>
					            <form:errors class="errormessage" path="passwordConfirmation"/>
					            <form:password class="input-box" path="passwordConfirmation" placeholder="Password (confirm)"/>
					        </div>
					        <input class="btn" type="submit" value="Sign up with email"/>
			        	</form:form>
			        	<div class="separator"></div>
			        	<p class="already">Already signed up? <a href="/users/showlogin">Go to login</a></p>
					</div>
				</div>
			</div>
		</main>
	</body>
</html>