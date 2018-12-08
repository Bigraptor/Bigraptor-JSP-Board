<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.BoardDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="board.Board" %>
<%@ page import="user.UserDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bigraptor JSP Board</title>
<link href="https://fonts.googleapis.com/css?family=Kumar+One+Outline" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link rel="stylesheet" href = "css/index.css">
<link rel="stylesheet" href = "css/board.css">
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
			UserDAO userDAO = new UserDAO();
			String nickName = userDAO.getNickname((String)session.getAttribute("id"));
	%>
		<div class = "login">
			<div class = "login-id">
				<i class = "fas fa-user"> </i><%= nickName %>
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
		<%
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDAO boardDAO = new BoardDAO();
			Board board = boardDAO.getBoard(no);
		%>
		<div class = "board_wrapper">
			<div class = "board_title">
				<%= board.getTitle() %>
				<div class = "board_sub">
					<span><%= board.getAuthor() %></span><span><%= board.getCreated() %></span>
				</div>
			</div>
			<div class = "board_content">
				<%= board.getContents() %>
			</div>
		</div>
		<%
			UserDAO userDAO = new UserDAO();
			String nickName = userDAO.getNickname((String)session.getAttribute("id"));
			if(session.getAttribute("id") != null){
				if(nickName.equals(board.getAuthor())){
		%>
		<div class = "subArea">
			<div class = "sub_button">
				<a href = "modifyServlet?no=<%= no %>"><span class = "button">수정</span></a>
				<a href = "deleteServlet?no=<%= no %>"><span class = "button">삭제</span></a>
			</div>	
		</div>	
		<%
				}
			}
		%>
	</div>	
</body>
</html>