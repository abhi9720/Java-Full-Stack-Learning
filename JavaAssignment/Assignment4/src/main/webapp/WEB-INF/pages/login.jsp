<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Form</title>
<style>
/* Global styles */
body {
	background-color: #f2f2f2;
	font-family: Arial, sans-serif;
	position: relative;
}

h1 {
	margin: 20px auto;
	text-align: center;
}

form {
	max-width: 500px;
	margin: auto;
	margin-top: 50px;
	border: 1px solid #ccc;
	padding: 20px;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 1px 3px 0 rgb(0 0 0/ 0.1), 0 1px 2px -1px rgb(0 0 0/ 0.1);
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: rgb(29, 78, 216);
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	transition: all 0.3s ease;
}

input[type="submit"]:hover {
	background-color: rgb(30 58 138);
}

input[type="reset"] {
	background-color: rgb(225, 29, 72);
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	transition: all 0.3s ease;
}

input[type="reset"]:hover {
	background-color: rgb(190 18 60);
}

#loginmsg {
	text-align: center;
	color: red;
}
</style>
</head>
<body>
	<h1>Admin Login Panel</h1>
	<form action="<%=request.getContextPath()%>/login" method="post">
		<label for="username">Username:</label> <input type="text"
			id="username" name="username" required> <label for="password">Password:</label>
		<input type="password" id="password" name="password" required>

		<input type="submit" value="Submit"> <input type="reset"
			values="reset">
			
			
			<%
	if (request.getAttribute("errorMessage") != null) {
	%>
	<p id="loginmsg">
		<%=request.getAttribute("errorMessage")%>
	</p>
	<%
	}
	%>
	</form>
</body>
</html>

</html>