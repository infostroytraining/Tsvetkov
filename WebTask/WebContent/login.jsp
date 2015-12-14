<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<c:if test='${not empty ErrorMsg}'>
	<c:forEach items="${ErrorMsg}" var="current">
	${current}
	<br>
	</c:forEach>
</c:if>
<form method="post" action="LoginServlet" >
	<div class="table">
		<div class="line">
			<div class="left">Login</div>
			<div class="right">
				<input type="text" name="login">
			</div>
		</div>
		<div class="line">
			<div class="left">Password</div>
			<div class="right">
				<input type="password" name="pass">
			</div>
		</div>
		<div class="line">
			<div class="left">
				<input type="submit" value="Login">
			</div>
			<div class="right">
				<input type="reset" value="Reset">
			</div>
		</div>
	</div>
</form>
</body>
</html>