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
<form action="signIn" method="POST">
	<input type="text" name="userId" value="계정명">
	<input type="password" name="userPw" value="비밀번호">
	<input type="email" name="userEmail" value="계정메일">
	<input type="text" name="userAdd" value="계정주소">
	<input type="submit" value="제출">
</form>
</body>
</html>
