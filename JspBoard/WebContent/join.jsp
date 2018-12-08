<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bigraptor Board 회원가입</title>
<link href="https://fonts.googleapis.com/css?family=Kumar+One+Outline" rel="stylesheet">
<link rel = "stylesheet" href = "css/join.css">
</head>
<body>
	<header id = "header">
		<div class = "header-logo">
			<img src = "img/bigraptor.png">
		</div>
		<div class = "header-title">
			<a href = "./index.jsp">Bigraptor Board</a>
		</div>
		<div>
			<h2>Bigraptor Board에 오신것을 환영합니다!</h2>
			<p>
				https://github.com/bigraptor 의 JSP 게시판 토이 프로젝트입니다.<br/>
				연습프로젝트이기 때문에 개인정보는 취급하지 않지만<br/>
				최소한의 정보만 입력하도록 했습니다. 감사합니다.
			</p>
		</div>
	</header>
	<div id = "container">
		<div class = "container-body">
			<form name = "accountForm" method = "post" action = "./UserServlet">
				<div class = "form-id">
					아이디
				</div>
				<div>
					<input type = "text" name = "id" placeholder = "아이디를 입력하세요." class = "form-form">
				</div>
				<div class = "form-id">
					비밀번호
				</div>
				<div>
					<input type = "password" name = "pw" placeholder = "비밀번호를 입력하세요." class = "form-form"required>
				</div>
				<div class = "form-id">
					닉네임
				</div>
				<div>
					<input type = "text" name = "nickname" placeholder = "닉네임을 입력하세요." class = "form-form">
				</div>
				<div class = "form-id">
					이메일
				</div>
				<div>
					<input type = "email" name = "email" placeholder = "이메일을 입력하세요." class = "form-form">
				</div>
				<button class = "button" onclick = "submit()">회원가입</button>
			</form>
		</div>
	</div>
	
	<script>
		function submit(){
			document.getElementById("accountForm").submit();
		}
	</script>
</body>
</html>