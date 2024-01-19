
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style type="text/css">
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f6f6f6;
	margin: 0;
	padding: 0;
	position: relative;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
}

button {
	cursor: pointer;
}

header {
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #fff;
	padding: 10px;
	border-bottom: 1px solid #D3D3D3;
	position: sticky;
	top: 0;
	width: 100%;
	z-index: 1000;
}

.headertitle {
	font-size: 28px;
	color: rgb(30, 58, 138);
	text-align: center;
	flex: 1;
	margin: 0;
}

.welcome {
	display: flex;
	align-items: center;
	position: absolute;
	right: 10px;
}

.welcome-message {
	font-weight:600;
	font-size: 14px;
	margin-right: 16px;
	color: rgb(64 64 64);
}

.logout-btn {
	border: none;
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	border-radius: 5px;
	background-color: rgb(30, 58, 138);
}
</style>
</head>
<body>
	<header class="header">
		<h1 class="headertitle">Library Management</h1>

		<c:if test="${sessionScope.loggedIn == true}">
			<c:if test="${not empty sessionScope.user}">
				<div class="welcome">
					<span class="welcome-message">Welcome,
						${sessionScope.user.firstName}</span> <a
						href="${pageContext.request.contextPath}/logout"
						class="logout-btn">Logout</a>
				</div>
			</c:if>
		</c:if>

	</header>

</body>
</html>