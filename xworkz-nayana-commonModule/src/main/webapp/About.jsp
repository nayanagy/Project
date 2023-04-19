<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}
nav {
	background-color: #333;
	color: #fff;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
}
nav ul {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
}
nav ul li {
	margin: 0 10px;
}
nav ul li a {
	color: #fff;
	text-decoration: none;
	padding: 5px 10px;
}
nav ul li a:hover {
	background-color: #555;
	border-radius: 5px;
}
nav img {
	height: 50px;
	padding: 5px;
	margin-right: auto;
}

main {
	padding: 50px;
}
main ul li {
	padding: 10px;
}
footer {
	align-content: center;
	text-align: center;
}
</style>
</head>
<body>
	<nav>
		<img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="">
		<ul>
			<li style="float:right"><a href="index.jsp">Home Page</a></li>
  			<li style="float:right"><a href="SignUp.jsp">SignUp</a></li>
 			<li style="float:right"><a href="SignIn.jsp">SignIn</a></li>
 			<li style="float:right"><a class="active" href="About.jsp">About</a></li>
		</ul>
	</nav>
	<main>
		<h1>About Us</h1>
		<p>Welcome to our website! We are a team of developers who are passionate about creating high-quality web applications that meet
			the needs of our clients.In this section,we would like to share some information about the development of some common modules that you may encounter on our site</p>
		<h2>Our Modules</h2>
		<ul>
			<li><strong>Sign Up:</strong>The sign up module is an essential component of any website that requires user registration.When designing this module,we focused on creating a simple and user-friendly interface
										 that guides the user through the registration process step-by-step.We also implemented various security measures,such as password hashing and email verification,to ensure that user data is protected.</li>
			<li><strong>Sign In:</strong> The sign in module allows users to login to our website securely.To achieve this,we use a combination of session management and encryption techniques to ensure that user data remains
										 confidential.We use password hashing and other security measures to prevent unauthorized access.</li>
			<li><strong>Mail Send:</strong>The send mail module allows users to send messages to other users or to the site administrators.We developed this module using popular email APIs,such as SMTP,to ensure that emails are
										 delivered reliably and efficiently. We use email authentication and other security measures to prevent spam and ensure deliverability </li>
		</ul>
	</main>
	<footer>
		<p>&copy; 2023 Nayana. All rights reserved.</p>
	</footer>
</body>
</html>