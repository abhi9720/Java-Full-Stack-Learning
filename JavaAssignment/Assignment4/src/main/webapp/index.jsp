<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>T-shirt Search</title>
<style>
body {
	background-color: #f2f2f2;
	font-family: Arial, sans-serif;
	margin: 0;
}

header {
	padding: 10px 20px;
	background-color: #fff;
	position: sticky;
	top: 0;
	width: 100%;
	border-bottom: 1px solid #d3d3d3;
}

.logout {
	background-color: #f50057;
	color: #fff;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	border-radius: 5px;
	margin-left: 10px;
	font-weight: 600;
	float:right;
}

.logout:hover {
	background-color: #c2185b;
}

.container {
	max-width: 600px;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	border: 1px solid #d3d3d3;
	box-shadow: 0 1px 3px 0 rgb(0 0 0/ 0.1), 0 1px 2px -1px rgb(0 0 0/ 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 30px;
	color: #484848;
}

form {
	border: 1px solid #ccc;
	display: flex;
	flex-direction: column;
	background-color: #f2f2f2;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 1px 3px 0 rgb(0 0 0/ 0.1), 0 1px 2px -1px rgb(0 0 0/ 0.1);
}

form>div {
	margin-bottom: 20px;
}

label {
	font-weight: bold;
	margin-right: 10px;
	color: #484848;
}

input[type="text"], select {
	padding: 10px;
	font-size: 16px;
	border: 2px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	width: 100%;
	color: #484848;
}

input[type="radio"] {
	margin-right: 5px;
}

input[type="submit"] {
	background-color: #1d4ed8;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	transition: all 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #1e3a8a;
}

datalist {
	display: none;
}

.error-msg {
	color: #f44336;
}

</style>
</head>
<body>


	<%
	Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

	if (loggedIn == null || !loggedIn) {
		response.sendRedirect(request.getContextPath() + "/login");
	}
	%>
	
	

	<div class="container">
		<a  class="logout" href="logout">Logout</a>

	

		<form autocomplete="off" action="search">



			<h1>T-shirt Search</h1>
			<div>
				<label for="colour">Color:</label> <input type="text" name="colour"
					id="colour" required="required">
			</div>
			<div>
				<label for="size">Size:</label> <input type="text" name="size"
					id="size" list="size-options" required="required">
				<datalist id="size-options">
					<option value="S">S</option>
					<option value="M">M</option>
					<option value="L">L</option>
					<option value="XL">XL</option>
				</datalist>
			</div>

			<div style="display: flex; gap: 20px;">
				<label>Gender:</label>
				<div style="display: flex; gap: 5px;">

					<label for="male"> <input type="radio" id="male"
						name="gender" value="m"> Male
					</label> <label for="female"> <input type="radio" id="female"
						name="gender" value="f"> Female
					</label> <label for=Unisex> <input type="radio" id="Unisex"
						name="gender" value="u"> Unisex
					</label>
				</div>
			</div>

			<div>
				<label for="sort-by">Sort By:</label> <select name="sortBy"
					id="sort-by" required="required">
					<option value="price">Price</option>
					<option value="rating">Rating</option>
					<option value="both">Both</option>
				</select>
			</div>
			<div>
				<input type="submit" value="Search">
			</div>


			<c:if test="${not empty param.errorMessage}">
				<strong class="error-msg">*${param.errorMessage}</strong>
			</c:if>
		</form>
	</div>
</body>
</html>
