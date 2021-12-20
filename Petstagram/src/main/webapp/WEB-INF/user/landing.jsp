<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in | Registration</title>
</head>
<body>
<div class="container">
<h1>Welcome to Pets-tagram</h1>
<div class="row">
<div class="col-6 offset-2">
<h3>Register</h3>
	<form:form action="/registerUser" method="post" modelAttribute="user">
		<div class="form-group">
			<form:label path="userName">User Name</form:label>
			<form:errors path="userName"></form:errors>
			<form:input class="form-control" path="userName"></form:input>
		</div>
		<div class="form-group">
			<form:label path="email">Email</form:label>
			<form:errors path="email"></form:errors>
			<form:input type="email" class="form-control" path="email"></form:input>
		</div>
		<div class="form-group">
			<form:label path="password">Password</form:label>
			<form:errors path="password"></form:errors>
			<form:input type="password" class="form-control" path="password"></form:input>
		</div>
		<div class="form-group">
			<form:label path="confirmPassword">Confirm Password</form:label>
			<form:errors path="confirmPassword"></form:errors>
			<form:input type="password" class="form-control" path="confirmPassword"></form:input>
		</div>
		<input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
</div>
<div class="col">
<h3>Login</h3>
<p>${loginError}</p>
	<form action="/login" method="POST">
	<div class="form-group">
		<label>Email Address</label>
		<input type="email"class="form-control" name="lemail">
	</div>
	<div class="form-group">
		<label>Password</label>
		<input type="password"class="form-control" name="lpassword">
	</div>
	<input class="btn btn-primary" type="submit" value="Login"/>
	</form>
</div>
</div>
</div>
</body>
</html>