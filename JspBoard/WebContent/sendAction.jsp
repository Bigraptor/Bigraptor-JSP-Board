<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bigraptor JSP Board</title>
<link href="https://fonts.googleapis.com/css?family=Kumar+One+Outline" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/sendAction.css">
</head>
<body>
	<header>
		<%
			if(session.getAttribute("id") == null) {
		%>
			<div class = "button-area">
				<span id = "login-button"><a href = "login.jsp"><i class = "fas fa-key"></i></a></span>
			</div>
		<%
			} else {
				String id = (String)session.getAttribute("id");
		%>
		<div class = "login">
			<div class = "login-id">
				<i class = "fas fa-user"> </i><%= id %>
			</div>
			<a href = "./LogoutServlet">
				<div class = "login-button">
					로그아웃
				</div>
			</a>
		</div>
		<%
			}
		%>
		<div class = "title">
			<a href = "index.jsp">JSP 게시판</a>
		</div>
	</header>
	<div class = "container">
		<div class = "box">
			인증을 위한 이메일을 전송하였습니다.<br>
			입력하신 이메일 주소로 인증을 진행해주세요.
		</div>
	</div>
</body>
</html>