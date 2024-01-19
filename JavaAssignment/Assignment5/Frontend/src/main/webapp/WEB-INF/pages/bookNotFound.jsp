<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
	
.error-message {
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  border-radius: 0.25rem;
  padding: 1rem;
  margin: 1rem auto;
  max-width: 600px;
  text-align: center;
}

h1 {
  color: rgb(37, 99, 235);
  font-size: 2rem;
  margin: 0.5rem 0;
}

p {
  margin: 1rem 0;
}

.homepagebtn {
  background-color: #007bff;
  border-color: #007bff;
  color: #fff;
  padding: 0.5rem 1rem;
  text-decoration: none;
  border-radius: 0.25rem;
}

.homepagebtn:hover {
  background-color: #0069d9;
  border-color: #0062cc;
  cursor: pointer;
}

</style>
</head>
<body>

  <div class="error-message">
        <h1>Book Not Found</h1>
        <p>The book you requested does not exist.</p>
        
        <a class="homepagebtn" href="${pageContext.request.contextPath}/books">Go to Homepage</a>
        
    </div>

</body>
</html>