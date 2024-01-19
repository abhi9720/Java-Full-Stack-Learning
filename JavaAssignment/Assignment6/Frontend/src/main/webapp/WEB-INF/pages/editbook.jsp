<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
/* Heading styles */
.formtitle {
  font-size: 24px;
  text-align: center;
}

table{
	width:100%;
}


.formwrapper {
  background-color: #f3f3f3;
  border: 1px solid #d3d3d3;
  border-radius: 5px;
  padding: 10px;
}

/* Form styles */
form {
  margin: 20px auto;
  padding: 10px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 500px;
}

label {
  display: block;
  margin-bottom: 10px;
  width: 100%;
}

input[type="text"],
select {
  padding: 5px;
  line-height: 1.5rem;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 20px;
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

input[readonly] {
  border: none;
  outline: none;
}

input[readonly]:focus {
  border: none;
  outline: none;
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

/* Error message styles */
.error {
  color: red;
  margin-bottom: 10px;
}

/* Custom styles */
.row {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 20px;
}

.col-1 {
  flex-basis: 40%;
  margin-right: 5%;
}

.col-2 {
  flex-basis: 55%;
}

@media only screen and (max-width: 600px) {
  .row {
    flex-direction: column;

</style>
</head>
<body>


	<%@ include file="../components/navbar.jsp"%>





	<form action="${pageContext.request.contextPath}/books/edit" method="post">
  <h1 class="formtitle">Edit Book</h1>
  <div class="formwrapper">
    <table>
      <tr>
        <td><label for="id"> Book Code:</label></td>
        <td><input id="id" type="text" name="id" value="${book.id}" readonly /></td>
      </tr>
      <tr>
        <td><label for="bookname">Name:</label></td>
        <td><input id="bookname" type="text" name="name" value="${book.name}" required/></td>
      </tr>
      <tr>
        <td><label for="author">Author:</label></td>
        <td>
          <select name="author.id">
            <c:forEach items="${authors}" var="author">
              <option value="${author.id}" ${author.id == book.author.id ? 'selected' : ''}>${author.name}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
      <tr>
        <td><label for="dateAdded">Date Added:</label></td>
        <td><input id="dateAdded" type="text" name="dateAdded" value='<fmt:formatDate value="${book.dateAdded}" pattern="d MMM yyyy"/>' readonly /></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="Update" /> 
          <a href="../../books" class="cancelbtn">Cancel</a>
        </td>
      </tr>
    </table>
  </div>
</form>

	<%@ include file="../components/footer.jsp"%>

</body>
</html>