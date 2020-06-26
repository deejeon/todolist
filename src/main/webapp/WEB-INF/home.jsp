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
		<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.png">
	</head>
	<body>
		<div class="header sticky" id="header">
<!-- 			<div class="logo"> -->
				<h1><a class="logo" href="/"><img class="main-logo" src="/img/header-logo-white.png" width="35" height="35">Todolist</a></h1>
<!-- 			</div> -->
			
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
				<div id="reviews-image">
					<div class="review">
						<div>
							<h4 class="reviewer">Google Play</h4>
							<div class="stars">★★★★★</div>
							<h4 class="review-content">Editor's Choice</h4>
							<h4 class="review-content">4.7 stars, 187K+ reviews</h4>
						</div>
					</div>
					<div class="review">
						<div>
							<h4 class="reviewer">App Store</h4>
							<div class="stars">★★★★★</div>
							<h4 class="review-content">Featured app</h4>
							<h4 class="review-content">4.8 stars, 30K+ reviews</h4>
						</div>
					</div>
					<div class="review">
						<div>
							<h4 class="reviewer">The Verge</h4>
							<h4 class="review-content">9/10</h4>
							<h4 class="review-content">"The best to-do list app</h4>
							<h4 class="review-content">right now"</h4>
						</div>
					</div>
				</div>
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