<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Page Not Found</title>
	<style>
		body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

section {
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

section div {
  text-align: center;
}

section div h1 {
  color: rgb(37, 99, 235);
  font-weight: bold;
  font-size: 3rem;
  margin-top: 1rem;
  margin-bottom: 0.5rem;
}

section div p {
  font-weight: bold;
  font-size: 1.5rem;
  margin-top: 0;
  margin-bottom: 1rem;
}

section div a {
  color: #fff;
  background-color: rgb(37, 99, 235);
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.2rem;
  transition: background-color 0.3s ease;
}

section div a:hover {
  background-color: rgb(57, 119, 255);
}

	</style>
</head>
<body>
	 <section>
          <div>
            <h1>404 Not Found</h1>
            <p>Whoops! That page doesn’t exist.</p>
            <a href="${pageContext.request.contextPath}/books">Back to Homepage</a>
          </div>
        </section>
</body>
</html>
