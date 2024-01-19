<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style type="text/css">
.container {
	margin: 20px auto;
	width: 500px;
	padding: 10px;
	background-color: #f3f3f3;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.formheadingwrapper {
	display: flex;
	align-content: center;
	justify-content: center;
	position: relative;
}

.formheadingwrapper h1 {
	font-size: 24px;
	text-align: center;
}

table {
	width: 100%;
}

.formwrapper {
	background-color: #fff;
	border: 1px solid #D3D3D3;
	border-radius: 5px;
	padding: 10px;
}

label {
	display: block;
	margin-bottom: 10px;
}

input[type="text"], select {
	padding: 5px;
	line-height: 1.5rem;
	font-size: 16px;
	border-radius: 5px;
	border: 1px solid #ccc;
	width: 100%;
	box-sizing: border-box;
	margin-bottom: 20px;
}

input[readonly] {
	border: none;
	outline: none;
}

input[readonly]:focus {
	border: none;
	outline: none;
}

input[type="submit"] {
	background-color: rgb(79, 70, 229);
	color: #fff;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

input[type="submit"]:hover {
	background-color: rgb(67, 56, 202);
}

.cancelbtn {
	background-color: rgb(225, 29, 72);
	color: #fff;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
	text-decoration: none;
}

.cancelbtn:hover {
	background-color: rgb(190, 18, 60);
}

option {
	padding: 10px;
}

.ErrorMsg {
	color: rgb(225, 29, 72);
	margin: 20px auto;
	text-align: center;
}
</style>
</head>
<body>


	<%@ include file="../components/navbar.jsp"%>
	<div class="container">
		<div class="formheadingwrapper">
			<h1>Add New Book</h1>
		</div>

		<div class="formwrapper">
			<form action="${pageContext.request.contextPath}/books/add"
				method="post">
				<table>
					<tr>
						<td><label for="id"> Book Code:</label></td>
						<td><input id="id" type="text" name="id" value="${book.id}" required/></td>
					</tr>
					<tr>
						<td><label for="bookname">Book Name:</label></td>
						<td><input id="bookname" type="text" name="name" value="${book.name}" required/></td>
					</tr>
					<tr>
						<td><label for="author">Author:</label></td>
						<td><select id="author" name="author.id">
								<c:forEach items="${authors}" var="author">
									<option value="${author.id}">${author.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label for="dateAdded">Date Added:</label></td>
						<td><input id="dateAdded" type="text" name="dateAdded"
							readonly /></td>
					</tr>

					<tr>
						<td colspan="2"><input type="submit" value="Add Book" /> <a
							href="${pageContext.request.contextPath}/books" class="cancelbtn">Cancel</a>
						</td>
					</tr>
				</table>
			</form>

			<p class="ErrorMsg">${bookConflict}</p>

		</div>
	</div>


	<%@ include file="../components/footer.jsp"%>


	<script>
  // Get the current date in the format you want
  const currentDate = new Date().toLocaleDateString('en-GB', { day: 'numeric', month: 'short', year: 'numeric' });

  // Set the input value to the current date
  document.getElementById('dateAdded').value = currentDate;
</script>
</body>
</html>