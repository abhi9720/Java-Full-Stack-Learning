<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDao"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Product</title>

<style type="text/css">
body {
	background-color: #f2f2f2;
	font-family: Arial, sans-serif;
}

h1 {
	color: #333;
	text-align: center;
	margin-top: 50px;
}

.customform {
	max-width: 60vw;
	margin: 50px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 1px 3px 0 rgb(0 0 0/ 0.1), 0 1px 2px -1px rgb(0 0 0/ 0.1);
}
.customform h2{
	text-align:center;
}

label {
	display: block;
	font-weight: bold;
	margin-bottom: 10px;
}

input[type="text"], input[type="number"], input[type="file"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 16px;
	resize: vertical;
}
.btnwrapper{
	text-align:center;
}


#editproduct {
	background-color: rgb(26 86 219);
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	transition: all 0.3s ease;
	margin-top: 20px;
	font-weight: 600;
}


#editproduct:hover {
	background-color: rgb(30 58 138);
}


#backbtn {
	background-color: #f50057;
	color: #fff;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	border-radius: 5px;
	margin-left: 10px;
	font-weight: 600;
	text-decoration: none;
}
#backbtn:hover{
	text-decoration: underline;
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

	 


	<form class="customform"
		action="<%=request.getContextPath()%>/edit-product?id=<%= ((Product)request.getAttribute("product")).getId() %>"
		method="post" enctype="multipart/form-data">

			<h2>Edit Product ID: <%= ((Product)request.getAttribute("product")).getId() %></h2>
		<div>
			<label for="title">Title:</label> <input type="text" id="title"
				name="title"
				value="<%= ((Product)request.getAttribute("product")).getTitle() %>"
				required>
		</div>
		<div>
			<label for="quantity">Quantity:</label> <input type="number"
				id="quantity" name="quantity"
				value="<%= ((Product)request.getAttribute("product")).getQuantity() %>"
				required>
		</div>
		<div>
			<label for="size">Size:</label> <input type="text" id="size"
				name="size"
				value="<%= ((Product)request.getAttribute("product")).getSize() %>"
				required>
		</div>
		<div>
			<label for="image">Image:</label> <input type="file" id="image"
				name="image" accept="image/*">
			<% 
    Product product = (Product)request.getAttribute("product");
    if (product.getImageFileName() != null) {
        String imagePath = request.getContextPath() + "/uploads/" + product.getImageFileName();
    %>
			<img src="<%= imagePath %>" alt="Product Image" width="200">
			<% } %>
		</div>


		<div class="btnwrapper">
			<button id="editproduct" type="submit">Update Product</button>
			<a id="backbtn" href="/app/productmanagement" >Cancel</a>
		</div>
	</form>

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