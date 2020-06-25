<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home | Todolist</title>
		<link rel="stylesheet" href="/css/home.css">
	</head>
	<body>
		<div class="header sticky" id="header">
			<h1><a href="/">Todolist</a></h1>
			<div class="header-links">
				<a href="/users/showlogin">Login</a>
				<a id="register" href="/users/showregister">Signup</a>
			</div>
		</div>
		<div class="content">
			<div id="getstarted">
				<p>Organize your life with Todolist</p>
				<a class="btn-link" href="/users/showregister">Get Started</a>
			</div>
			<div id="info">
				<h1>Free up your mental space</h1>
				<p>Regain clarity and calmness by getting all those tasks out of your head and onto your to-do list (no matter where you are or what device you use).</p>
			</div>
			<section class="reviews">
				<div id="reviews-image"></div>
				<!-- <div class="reviews-container">
					<div class="review">
						<p class="reviewer">Google Play</p>
						<div>
							<div class="stars">★★★★★</div>
							<h4 class="review-content">Editor's Choice</h4>
							<h4 class="review-content">4.7 stars, 187K+ reviews</h4>
						</div>
					</div>
					<div></div>
					<div></div>
				</div> -->
			</section>
			
		</div>
		
		<script>
		window.onscroll = function() {myFunction()};
		
		var header = document.getElementById("myHeader");
		var sticky = header.offsetTop;
		
		function myFunction() {
		  if (window.pageYOffset > sticky) {
		    header.classList.add("sticky");
		  } else {
		    header.classList.remove("sticky");
		  }
		}
		</script>
	</body>
</html>