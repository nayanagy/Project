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
 			
 			<li style="float:right"><a class="active" href="About.jsp">About</a></li>
		</ul>
	</nav>
        <div id="login" class="login-box">
          <h1>Welcome to login page</h1>
          <span style="color: red">${lockMsg}</span>
          <span style="color: red">${msgs}</span>
          <span style="color: red">${message}</span>
          <form action="signIn" method="post">
            <div class="field-wrap">
            <label>
              User Id<span class="req">*</span>
            </label>
            <input type="text" name="userId" placeholder="Enter your userId" id="userId" onchange="validUserId()" required autocomplete="off"/>
            <span id="nameError" style="color: red;"></span>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password" name="password" id="userPassword" placeholder="Enter your password" required autocomplete="off"/>
           <div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" onchange="myFunction()">
			<label class="form-check-label">Show Password</label>
			</div>
          </div>
          <p class="forgot"><a href="ResetPassword.jsp">Forgot Password?</a></p>
          <input type="submit" value="SignIn" class="btn btn-primary" />
          </form>
          <p><a href="SignUp.jsp">Don't have an account?SignUp</a></p>
          <script>
          
          function myFunction() {
			var x = document.getElementById("userPassword");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
				}
			}
          
          function validUserId() {
        		var user = document.getElementById('userId');
        		var uservalue = user.value;
        		console.log(uservalue);
        		if (uservalue != null && uservalue != "" && uservalue.length > 4
        				&& uservalue.length < 20) {
        			console.log('Valid name');
        			document.getElementById('nameError').innerHTML = '';
        		} else {
        			console.log('Invalid name');
        			document.getElementById('nameError').innerHTML = 'Invalid userId,please enter min 4 and max 20';
        		}
        	}
          </script>
        </div>
</body>
</html>