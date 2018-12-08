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
</head>
<body>

	<%
		String search = "";
		int pageNumber = 0;
	
		if(request.getParameter("search") != null){
			search = request.getParameter("search");
		}
		if(request.getParameter("pageNumber") != null){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
	%>

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
		<div class = "search">
			<form method = "get" action = "./index.jsp">
				<input type = "text" name = "search" placeholder = "제목을 검색하세요." id = "titleSearch" >
				<input type = "submit" value = "Search">
			</form>
		</div>
	</header>
	<div class = "container">
		<div class = "table">
			<table>
				<thead>
					<tr>
						<th id = "row1">번호</th>
						<th id = "row2">제목</th>
						<th id = "row3">작성자</th>
						<th id = "row4">날짜</th>
					</tr>
				</thead>
				<tbody id = "board">
					<%
						BoardDAO boardDAO = new BoardDAO();
						ArrayList<Board> board = boardDAO.getList(search, pageNumber);
						Iterator itr = board.iterator();
						int count = 1;
						while(itr.hasNext()){
							if(count >= 11) break;
							Board tmp = (Board)itr.next();
					%>
						<tr>
							<td><%= tmp.getNo() %></td>
							<td><a href = "board.jsp?no=<%= tmp.getNo() %>"><%= tmp.getTitle() %></a></td>
							<td><%= tmp.getAuthor() %></td>
							<td><%= tmp.getCreated() %></td>
						</tr>
					<%
							++count;
						}
					%>
				</tbody>
			</table>
			<ul class = "pagination">
				<li>
					<% 
						if(pageNumber <= 0){
					%>
					<span class = "pagination_button disabled">이전</span>
					<%
						} else {
					%>
					<a href = "./index.jsp?pageNumber=<%= pageNumber-1 %>" class = "pagination_button">이전</a>
					<%
						}
					%>
				</li>
				<li>
					<%
						if(board.size() < 11){
					%>
					<span class = "pagination_button disabled">다음</span>
					<%
						} else{
					%>
					<a href = "./index.jsp?pageNumber=<%= pageNumber+1 %>" class = "pagination_button">다음</a>
					<%
						}
					%>
				</li>
			</ul>
		</div>
		<%
			if(session.getAttribute("id") != null){
		%>
		<div class = "write-area">
			<a href = "./write.jsp"><span><i class = "fas fa-pen">글쓰기</i></span></a>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>