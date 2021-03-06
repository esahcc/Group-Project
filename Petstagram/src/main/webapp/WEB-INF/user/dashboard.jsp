<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
<div class="container">
<div><a href="/dashboard">Dashboard </a><a href="/profile/${currentUser.id}">| My Photos </a><a href="/show">| Upload </a><a href="/logout">| Logout</a></div>
<h1>Welcome ${currentUser.userName} !</h1>

	<c:forEach items="${pictures}" var="pic">
	<h3><a href="/profile/${pic.user.id}">${pic.user.userName}</a> shared:</h3>
    	<h4><img src="${pic.image_url}" height="250" width="300"></h4>
    	<p>${pic.description}</p>
    	<c:choose>
    	<c:when test="${pic.likers.contains(user))}">
    	You liked this!
    	</c:when>
    	<c:otherwise>
    	<a href="/like/${pic.id}"> Like</a>
    	</c:otherwise>
    	</c:choose>
    	</c:forEach>
    	
    	
    	
    	
    	
    	<%-- <c:choose>
      	<c:when test="${pictureDetails.likers.contains(user)}">
      	|<a href="/unlike/${pic.id}"> Unlike</a>
      	</c:when>
      	<c:otherwise>
      	|<a href="/like/${pic.id}"> Like</a> </c:otherwise>
      	</c:choose>
    </c:forEach> --%>
    
 </div>
</body>
</html>