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
          <h1>Update password</h1>
          <span style="color: green">${message2}</span>
          <form action="updatePassword" method="post">
			
        <div class="mb-3">
			<label for="formFile" class="form-label">User Id</label> <input
				type="text" class="form-control" name="userId" id="userId" required="required"
				placeholder="Enter user Id" value="${userID}" readonly="readonly"/>
		</div>
		<div class="mb-3">
			<label for="formFile" class="form-label">Update New Password</label> <input
				type="password" class="form-control" name="password" id="userPassword" required="required"
				placeholder="Enter password"/>
		<div class="form-check mb-3">
		<input class="form-check-input" type="checkbox" onchange="myFunction()">
		<label class="form-check-label">Show Password</label>
		</div>
				<span id="passwordError" style="color: red"></span>
		</div>
		<div class="mb-3">
			<label for="formFile" class="form-label">Confirm password</label> <input
				type="password" class="form-control" name="confirmPassword" id="userConfirmPassword" required="required"
				placeholder="Enter confirm password" onchange="validPassword()"/>
		<div class="form-check mb-3">
		<input class="form-check-input" type="checkbox" onchange="myFunction1()">
		<label class="form-check-label">Show Password</label>
		</div>
				<span id="passwordCompare" style="color: red"></span>
		</div>
          <input type="submit" value="Update Password" class="btn btn-primary" />
          </form>
          <p><a href="SignIn.jsp">LoginPage?SignIn</a></p>
          <script>
          
          function myFunction() {
  			var x = document.getElementById("userPassword");
  			if (x.type === "password") {
  				x.type = "text";
  			} else {
  				x.type = "password";
  			}
  		}
  		function myFunction1() {
  			var x = document.getElementById("userConfirmPassword");
  			if (x.type === "password") {
  				x.type = "text";
  			} else {
  				x.type = "password";
  			}
  		}
          
          function validPassword() {
				var userPassword = document.getElementById('userPassword');
				var userConfirmPassword = document
						.getElementById('userConfirmPassword');
				var userPasswordvalue = userPassword.value;
				var userConfirmPasswordvalue = userConfirmPassword.value;
				console.log(userPasswordvalue);
				if (userPasswordvalue != null && userPasswordvalue != ""
						&& userPasswordvalue.length > 4
						&& userPasswordvalue.length < 12) {
					if (userPasswordvalue == userConfirmPasswordvalue) {
						console.log('valid both password are same');
						document.getElementById('passwordCompare').innerHTML = '';
					} else {
						console.log('valid both password are not same');
						document.getElementById('passwordCompare').innerHTML = 'Password and ConfirmPassword are not matching';
					}
					console.log('valid password');
					document.getElementById('passwordError').innerHTML = '';
				} else {
					console.log('invalid password');
					document.getElementById('passwordError').innerHTML = 'Plese enter valid password length must be greater then 4 and less then 12';
				}
			}
          </script>
        </div>
</body>
</html>