<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Error</title>
	<style>
		body {
			background-color: #f8f8f8;
			font-family: Arial, sans-serif;
		}
		.container {
			max-width: 800px;
			margin: 0 auto;
			padding: 50px;
			background-color: #fff;
			border: 1px solid #ddd;
			box-shadow: 0 0 10px rgba(0,0,0,0.1);
			text-align: center;
		}
		h1 {
			font-size: 48px;
			margin-bottom: 20px;
			color: #ff0000;
		}
		p {
			font-size: 24px;
			margin-bottom: 30px;
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>500 Internal Server Error</h1>
		<p><%=request.getAttribute("javax.servlet.error.message")%></p>
	</div>
</body>
</html>
