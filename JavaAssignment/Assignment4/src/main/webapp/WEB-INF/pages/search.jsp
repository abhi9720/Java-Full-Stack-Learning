<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Search Result</title>

	<style>
		/* Body Styles */
		body {
			font-family: Arial, sans-serif;
			background-color: #f5f5f5;
			margin: 0;
			padding: 0;
		}
#headertitle {
  text-align: center;
  font-size: 28px;
  color: rgb(30, 58, 138);
}

table {
  border-collapse: collapse;
  margin: 50px auto;
  width: 80%;
  max-width: 800px;
  background-color: #fff;
  box-shadow: 0px 0px 5px #aaa;
}

th, td {
  text-align: center;
  padding: 10px;
}

th {
  background-color: rgb(30, 58, 138);
  color: #fff;
}

tr:nth-child(even) {
  background-color: #f5f5f5;
}

tr:hover {
  background-color: #e5ffe5;
}

td {
  border: 1px solid rgb(30, 58, 138);
}

a {
  color: rgb(30, 58, 138);
}
.backbtn{
	
	text-align: center;
}

	</style>
</head>

<body>
	<h1 id="headertitle">Search Result</h1>
	<p class="backbtn"><a href="<%=request.getContextPath()%>">Search Another Query</a></p>

	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Color</th>
				<th>Size</th>
				<th>Gender</th>
				<th>Price</th>
				<th>Rating</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="item" items="${result}">
				<tr>
					<td>${item.name}</td>
					<td>${item.colour}</td>
					<td>${item.size}</td>
					<td>${item.genderRecommendation}</td>
					<td>${item.price}</td>
					<td>${item.rating}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
