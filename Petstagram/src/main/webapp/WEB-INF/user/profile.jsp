<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<div class = "container">
<div><a href="/dashboard">Dashboard </a><a href="/profile/${currentUser.id}">| My Photos </a><a href="/show">| Upload </a><a href="/logout">| Logout</a></div>
<h1>My Uploads</h1>

<c:forEach items="${currentUser.myPic}" var="pic">
<img src = "${pic.image_url}" height="200" width="250">
<p>${pic.description}</p>
<c:choose>
<c:when test="${pictureDetails.user.id == loggedInUser}">
<a href="/delete/${pictureDetails.id}">Delete</a>
</c:when>
<c:otherwise>
</c:otherwise>
</c:choose>
</c:forEach>

</div>
</body>
</html>