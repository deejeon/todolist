<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login | Todolist</title>
		<link rel="stylesheet" href="/css/login.css">
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
						<h1>Login</h1>
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
			        	<p class="errormessage"><c:out value="${error}" /></p>
					    <form method="POST" action="/login">
					        <div class="form-div">
					            <!-- <label for="email">Email</label> -->
					            <input class="input-box" type="text" id="email" name="email" placeholder="Email address"/>
					        </div>
					        <div class="form-div">
					            <!-- <label for="password">Password</label> -->
					            <input class="input-box" type="password" id="password" name="password" placeholder="Password"/>
					        </div>
					        <input class="btn" type="submit" value="Login"/>
					    </form>
			        	<div class="separator"></div>
			        	<p class="already">Don't have an account? <a href="/users/showregister">Sign up</a></p>
					</div>
				</div>
			</div>
		</main>
	</body>
</html>