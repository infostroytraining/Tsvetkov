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
<form method="post" action="RegServlet">
	<div class="table">
		<div class="line">
			<div class="left">First name</div>
			<div class="right">
				<input type="text" name="fname">
			</div>
		</div>
		<div class="line">
			<div class="left">Last name</div>
			<div class="right">
				<input type="text" name="lname">
			</div>
		</div>
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
			<div class="left">Confirm password</div>
			<div class="right">
				<input type="password" name="cpass">
			</div>
		</div>
		<div class="line">
			<div class="left">Email</div>
			<div class="right">
				<input type="text" name="email">
			</div>
		</div>
		<div class="line">
			<div class="left">Add photo</div>
			<div class="right">
				<input type="file" name="photo">
			</div>
		</div>
		<div class="line">
			<div class="left">
				<input type="submit" value="Register">
			</div>
			<div class="right">
				<input type="reset" value="Reset">
			</div>
		</div>
	</div>
</form>
</body>
</html>