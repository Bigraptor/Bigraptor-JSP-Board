<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "user.UserDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bigraptor Board Write</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link rel = "stylesheet" href = "css/write.css">
</head>
<body>
<%
	UserDAO userDAO = new UserDAO();
	if(session.getAttribute("id") == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	if(!userDAO.emailChecked((String)session.getAttribute("id"))){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('메일인증이 되어있지 않아 글을 작성할 수 없습니다. 메일인증을 진행해주세요.')");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
%>
	<header>
		<div class = "button-area">
			<span id = "login-button"><a href = "login.jsp"><i class = "fas fa-key"></i></a></span>
		</div>
		<div class = "title">
			<a href = "index.jsp">JSP 게시판</a>
		</div>
	</header>
	<div class = "container">
		<form method = "post" action = "./BoardWriteServlet">
			<div>
				<input type = "text" name = "title" placeholder = "제목을 입력하세요.">
			</div>
			<div class = "contents">
				<textarea name = "contents" placeholder = "내용을 입력하세요."></textarea>
			</div>
			<div class = "write-area">
				<input type = "submit" value = "작성">
			</div>
		</form>
	</div>
</body>
</html>