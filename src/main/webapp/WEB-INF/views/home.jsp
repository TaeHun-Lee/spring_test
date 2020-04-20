<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
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
<a href="user/userSignOut">로그아웃</a>
</body>
<script>
	$("#userSignUpForm").submit(function(e){
		e.preventDefault();
		var qs = $(this).serializeObject();
		console.log(JSON.stringify(qs));
		$.ajax({
		    url: "user/userSignUp",
		    type: "POST",
		    async : false,
		    data: JSON.stringify(qs),
		    contentType: "application/json; charset=utf8",
		    success: function(data) {
		        console.log(data);
		    },
		    error: function(errorThrown) {
		        alert(errorThrown.statusText);
		    }
		});
	});
    $.fn.serializeObject = function() { 
        var obj = null; 
        try { 
            if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
                var arr = this.serializeArray(); 
                if(arr){ 
                	obj = {}; 
                	$.each(arr, function() { 
                    obj[this.name] = this.value; 
                    }); 
                }
            }
        } catch(e) { 
            alert(e.message); 
        }
        return obj; 
   }
</script>
</html>
