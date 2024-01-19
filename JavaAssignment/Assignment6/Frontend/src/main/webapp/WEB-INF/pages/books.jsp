<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Books</title>

<style type="text/css">

button {
	cursor: pointer;
}

.heading {
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 24px;
}

.headingtitle {
	font-size: 28px;
	text-align: center;
	flex: 1;
}

.addnewBookbtn {
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	border: none;
	border-radius: 5px;
	background-color: rgb(37, 99, 235);
	margin-left: 16px;
	border-radius: 5px;
	position: absolute;
	right: 2.5vw;
	font-size: 16px;
}

table {
	width: 95vw;
	margin: 0 auto;
	background-color: white;
	box-shadow: 0px 0px 10px #ccc;
	padding: 5px;
	border-radius: 10px;
	margin-bottom: 20px;
	overflow: hidden;
}

th, td {
	text-align: left;
	padding: 10px;
}

td {
	text-align: center;
	border: 1px solid #e5e7eb;
	max-width: calc(100vw/ 5);
}

th {
	background-color: rgb(29, 78, 216);
	color: white;
	text-align: center;
	
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #e5f7ff;
}

.edit, .delete {
	color: white;
	padding: 4px 6px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	border-radius: 5px;
	font-size: 14px;
	margin: 2px;
}

.edit {
	background-color: rgb(37, 99, 235);
}

.delete {
	background-color: rgb(225, 29, 72);
}
</style>
</head>
<body>


	<%@ include file="../components/navbar.jsp"%>



	<div class="heading">
		<h1 class="headingtitle">Book Listing</h1>
		<a href="<%=request.getContextPath()%>/books/add"
			class="addnewBookbtn">Add a Book</a>
	</div>
	<table>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Date Added</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.name}</td>
				<td>${book.author.name}</td>
				
				<td><fmt:formatDate value="${book.dateAdded}" pattern="dd MMM yyyy"/></td>


				<td class="actions"><a class="edit"
					href="<%=request.getContextPath()%>/books/edit/${book.id}">Edit
						&#9998;</a> <a class="delete"
					href="<%=request.getContextPath()%>/books/delete/${book.id}"
					onclick="return confirm('Are you sure you want to delete this book?');">
						Delete &#10007;</a></td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="../components/footer.jsp"%>
	
</body>
</html>
