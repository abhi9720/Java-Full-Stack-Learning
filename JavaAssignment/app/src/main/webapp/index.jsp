<!DOCTYPE html>
<html>
<head>
<title>Product Management Tool</title>
<style>
body {
	margin: 0;
	padding: 0;
	background-color: #f8f8f8;
	font-family: Arial, sans-serif;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 40px;
	background-color: #fff;
	border: 1px solid #ddd;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

h1 {
	margin-top: 0;
	font-size: 36px;
	color: #333;
}

p {
	font-size: 18px;
	line-height: 1.5;
	margin-bottom: 20px;
}

a {
	display: inline-block;
	padding: 10px 20px;
	background-color: #333;
	color: #fff;
	text-decoration: none;
	border-radius: 4px;
	transition: background-color 0.3s ease;
}

a:hover {
	background-color: #555;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Welcome to the Product Management Tool</h1>
		<p>This tool helps you manage your products easily and
			efficiently.</p>
		<% if (request.getSession().getAttribute("loggedIn") != null) { %>
		<p>You are already logged in!</p>
		<a href="productmanagement">Go to Product Management</a>
		<% } else { %>
		<p>Please login to start managing your products:</p>
		<a href="login">Login</a>
		<% } %>
	</div>
</body>
</html>