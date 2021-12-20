<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload</title>
</head>
<body>
<div class = "container">
<div><a href="/dashboard">Dashboard </a><a href="/profile/${currentUser.id}">| My Photos </a><a href="/show">| Upload </a><a href="/logout">| Logout</a></div>
<h3>Upload a photo!</h3>
	<form method="POST" action="/upload" enctype="multipart/form-data">
		<div class="form-data">Select File: <input type="file" name="pic"></div>
		<div class="form-data"><textarea name="description" placeholder="Please enter a description"></textarea> <button>Upload Pic</button></div>
	</form>
</div>
</body>
</html>