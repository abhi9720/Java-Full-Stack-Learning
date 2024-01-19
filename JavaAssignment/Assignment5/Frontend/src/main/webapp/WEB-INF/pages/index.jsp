<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Library Management System</title>
<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content:center;
  padding: 20px;
  border-radius: 10px;
 	flex:1;
 	
 	    /* background-position: bottom right; */
    background-image: url(https://www.skoolbeep.com/blog/wp-content/uploads/2020/12/WHAT-SYSTEM-DOES-A-LIBRARY-USE-min.png), url(https://www.skoolbeep.com/blog/wp-content/uploads/2020/12/HOW-DO-YOU-DESIGN-A-LIBRARY-MANAGEMENT-SYSTEM-min.png);
    background-repeat: no-repeat, no-repeat;
    background-position: left bottom, right bottom;
    background-size: contain;
}

h1 {
  color:#333;
  margin-bottom: 30px;
  max-width: 50%;
  text-align: center;
  
}

form {
  padding:10px;
  width: max(400px , 35%);
  display: flex;
  flex-direction: column;
  align-items: center;
  border:1px solid #d3d3d3;
  background-color: white;
  border-radius: 5px;
}

input[type="email"],
input[type="password"],
input[type="submit"] {
  padding: 10px;
  margin: 10px 0;
  width: 100%;
  border: 1px solid rgb(37, 99, 235);
  border-radius: 5px;
  font-size: 16px;
}

input[type="submit"] {
  background-color: rgb(37, 99, 235);
  color: #fff;
  cursor: pointer;
  transition:all 0.3s;
}

input[type="submit"]:hover {
  background-color: rgb(37, 99, 215);
}

.loginErrorMsg {
  color: red;
  margin-top: 20px;
}

.homepagebtn {
  background-color: rgb(37, 99, 235);
  color: #fff;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

.homepagebtn:hover {
   background-color: rgb(37, 99, 215);
}

/* Media Query for smaller devices */
@media (max-width: 768px) {
  .container {
    padding: 30px;
  }
}
</style>
</head>
<body>

	<%@ include file="../components/navbar.jsp"%>
	<div class="container">

<c:if test="${sessionScope.loggedIn == true}">
		<h1>Welcome to the Library Management System</h1>

		<a class="homepagebtn" href="${pageContext.request.contextPath}/books">
			Go to Library Portal
		</a>
</c:if>
<c:if test="${sessionScope.loggedIn != true}">
		<h1>Login To Continue</h1>

		<form action="${pageContext.request.contextPath}/login" method="post">
			<input type="email" name="email" placeholder="Email"  minlength="5" maxlength="50" required />
			<input type="password" name="password" placeholder="Password"  minlength="5" maxlength="50" required /> 
			<input value="Login" type="submit" />
		</form>
</c:if>
		
		
		<div class="loginErrorMsg">${error}</div>
	

	</div>
	
		<%@ include file="../components/footer.jsp"%>
	
</body>
</html>




