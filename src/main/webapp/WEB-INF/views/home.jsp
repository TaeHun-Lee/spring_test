<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<P>  The time on the server is ${serverTime}. </P>
<%
	HttpSession session = request.getSession();
	Object obj = session.getAttribute("signedUser");
%>
<%= obj %>
<jsp:include page="userSignInForm.jsp"></jsp:include>
<jsp:include page="userSignUpForm.jsp"></jsp:include>
<jsp:include page="userModifyForm.jsp"></jsp:include>
<a href="userSignOut">로그아웃</a>
</body>
</html>
