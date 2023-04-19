<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap"
      rel="stylesheet"/>
    <style>
    body {
  background-color: #344a72;
  font-family: "Roboto", sans-serif;
}
.login-box {
  width: 360px;
  height: auto;
  margin: auto;
  border-radius: 3px;
  background-color: white;
}

h1 {
  text-align: center;
  padding-top: 15px;
}

h4 {
  text-align: center;
}

form {
  width: 300px;
  margin-left: 20px;
}

form label {
  display: flex;
  margin-top: 20px;
  font-size: 18px;
}

form input {
  width: 100%;
  padding: 7px;
  border: none;
  border: 1px solid gray;
  border-radius: 6px;
  outline: none;
}
input[type="button"] {
  width: 320px;
  height: 35px;
  margin-top: 20px;
  border: none;
  background-color: #49c1a2;
  color: red;
  font-size: 18px;
}
p {
  text-align: center;
  padding-top: 20px;
  font-size: 15px;
}
.para-2 {
  text-align: center;
  color: white;
  font-size: 15px;
  margin-top: -10px;
}
.para-2 a {
  color: #49c1a2;
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
        <div id="login" class="login-box">
          <h1>Reset password</h1>
          <span style="color: green">${message1}</span>
        <span style="color: red">${message2}</span>
          <form action="reset" method="post">
         <div class="mb-3">
		 <label for="formFile" class="form-label">Email</label> <input
				type="email" class="form-control" name="email" id="emailId" required="required"
				placeholder="Enter email" onchange="onEmail()"/>
				  <span id="emailError" style="color: red"></span>
				 <!--  <span id="display" style="color: red"></span>-->
		 </div>
          <input type="submit" value="Reset" class="btn btn-primary" />
          </form>
          <p><a href="SignIn.jsp">LoginPage?SignIn</a></p>
          <script>
          
          function onEmail(){
  			var userEmail = document.getElementById('emailId');
  			var userEmailvalue = userEmail.value;
  			console.log(userEmailvalue);
  			if (userEmailvalue != null && userEmailvalue != ""
  					&& userEmailvalue.length > 4 && userEmailvalue.length < 40) {
  				console.log('valid email');
  				const xhttp = new XMLHttpRequest();
  				console.log('Running in ajax');
  				console.log(userEmailvalue);
  				xhttp.open("GET", "http://localhost:8081/xworkz-nayana-commonModule/email/"
  						+ userEmailvalue);
  				xhttp.send();
  				xhttp.onload = function() {
  					console.log(this);
  					<!--document.getElementById("display").innerHTML = this.responseText-->
  				}
  				document.getElementById('emailError').innerHTML = '';
  			} else {
  				console.log('invalid email');
  				document.getElementById('emailError').innerHTML = 'Plese enter valid email min 4 and max 40 charactes ';
  			}
  		}
          </script>
        </div>
</body>
</html>