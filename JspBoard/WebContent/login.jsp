<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bigraptor Board 로그인</title>
<link href="https://fonts.googleapis.com/css?family=Kumar+One+Outline" rel="stylesheet">
<link rel = "stylesheet" href = "css/login.css">
</head>
<body>
	<header id = "header">
		<div class = "header-logo">
			<img src = "img/bigraptor.png">
		</div>
		<div class = "header-title">
			<a href = "./index.jsp">Bigraptor Board</a>
		</div>
	</header>
	<div id = "container">
		<div class = "container-body">
			<form name = "accountForm" method = "post" action = "./LoginServlet">
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
					<input type = "password" name = "pw" placeholder = "비밀번호를 입력하세요." class = "form-form">
				</div>
				<button class = "button" onClick = "submit()">로그인</button>
			</form>
			<div class = "join-area">
				<a href = "./join.jsp">회원가입</a>
			</div>
		</div>
	</div>
	
	<script>
		function submit(){
			document.getElementById('accountForm').submit();
		}
	</script>
</body>
</html>