<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>
<%@page import="model.user"%>
<%@page import="dao.ProductDao"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
/* Reset styles */
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

/* Global styles */
body {
	background-color: #f2f2f2;
	font-family: Arial, sans-serif;
	position: relative;
}

/* Header styles */
header {
	padding: 10px 20px;
	background-color: #fff;
	position: sticky;
	top: 0;
	width: 100%;
	border-bottom: 1px solid #d3d3d3;
}

header h1 {
	margin: 0;
	text-align: center;
	font-size: 24px;
}

header form {
	display: flex;
	align-items: center;
	margin: unset;
}

header form span {
	margin-right: 10px;
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
}

.logout:hover {
	background-color: #c2185b;
}

main {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}

h1 {
	color: rgb(30, 41, 59);
	text-align: center;
	margin-top: 50px;
}

form {
	margin: 20px auto;
	max-width: 500px;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 1px 3px 0 rgb(0 0 0/ 0.1), 0 1px 2px -1px rgb(0 0 0/ 0.1);
}

.customform {
	max-width: 80vw;
	margin-bottom: 50px
}

label {
	display: block;
	font-weight: bold;
	margin-bottom: 10px;
}

input[type="text"], input[type="number"], input[type="file"], textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	resize: vertical;
}

form button {
	background-color: rgb(26, 86, 219);
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 0.5rem;
	cursor: pointer;
	font-size: 16px;
	transition: all 0.3s ease;
}

form button:hover {
	background-color: rgb(30, 58, 138);
}

table {
	width: 100%;
	margin: 0 auto;
	background-color: rgb(248 250 252);
	box-shadow: 0px 0px 10px #ccc;
	border-collapse: collapse;
}

th, td {
	text-align: left;
	padding: 10px;
}

td {
	text-align: center;
	border: 1px solid #e5e7eb;
}

th {
	background-color: rgb(29, 78, 216);
	color: white;
	text-align: center;
	border-bottom:3px solid #D3D3D3	
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.edit, .delete {
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	border-radius: 5px;
}

.edit {
	background-color: rgb(37, 99, 235);
}

.delete {
	background-color: rgb(225, 29, 72);
}

/* Form styles */
form h2 {
	color: rgb(30, 41, 59);
	margin-top: 30px;
	margin-bottom: 20px;
	font-size: 28px;
	text-align: center;
}

form button {
	margin-top: 20px;
}

form div:last-child {
	text-align: center;
}

.table-img {
	display: flex;
	justify-content: center;
	align-content: center;
	object-fit: cover;
}

.table-img img {
	width: 200px;
	height: auto;
}
</style>
</head>
<body>


	<%
	Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

	if (loggedIn == null || !loggedIn) {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	%>



	<header>
		<div
			style="display: flex; justify-content: space-between; align-items: center;">
			<h1 style="margin: 0 auto;">Product Management Tool</h1>

			<%
			if (session != null && !session.isNew()) {
				String username = null;

				username = (String) session.getAttribute("user");

				if (username != null) {
			%>
			<span>Welcome <strong><%=username%></strong>
			</span>
			<%
			}
			}
			%>



			<a href="<%=request.getContextPath()%>/logout" class="logout">
				Logout </a>

		</div>
	</header>


	<main>



		<form class="customform" action="productmanagement/add" method="post"
			enctype="multipart/form-data">

			<h2>Add Product to Stock</h2>
			<div>
				<label for="title">Title:</label> <input type="text" id="title"
					name="title" required>
			</div>
			<div>
				<label for="quantity">Quantity:</label> <input type="number"
					id="quantity" name="quantity" required>
			</div>
			<div>
				<label for="size">Size:</label> <input type="text" id="size"
					name="size" required>
			</div>
			<div>
				<label for="image">Image:</label> <input type="file" id="image"
					name="image" accept="image/*" required>
			</div>
			<div>
				<button id="addproduct" type="submit">Add Product</button>
			</div>
		</form>

		<table>
			<thead>
				<tr>
					<th>S. No</th>
					<th>Title</th>
					<th>Quantity</th>
					<th>Size</th>
					<th>Image</th>
					<th>Actions</th>
				</tr>
			</thead>

			<tbody>
				<%
				List<Product> productList = ProductDao.getAllProduct();
				System.out.println(productList);
				for (Product product : productList) {
				%>
				<tr>
					<td><%=product.getId()%></td>
					<td><%=product.getTitle()%></td>
					<td><%=product.getQuantity()%></td>
					<td><%=product.getSize()%></td>

					<td class="table-img"><img
						src="<%=request.getContextPath()%>/uploads/<%=product.getImageFileName()%>"
						height="100"></td>

					<td class="actions"><a class="edit"
						href="<%=request.getContextPath()%>/edit-product?id=<%=product.getId()%>">Edit
							&#9998;</a> <a class="delete"
						href="<%=request.getContextPath()%>/delete-product?id=<%=product.getId()%>">Delete
							&#10007;</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</main>

	<script>
	  var maxSize = 1 * 1024 * 1024; // 1MB in bytes
	  document.getElementById('image').addEventListener('change', function() {
	    if (this.files[0].size > maxSize) {
	      alert('Maximum file size is 1MB.');
	      this.value = ''; // Clear the input field
	    }
	  });
	</script>
</body>
</html>