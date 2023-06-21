<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- <link rel="stylesheet" href="jsp/style.css"> -->

<style>
@charset "EUC-KR";

.box {
	box-sizing: border-box;
	font-family: 'Noto Sans KR', sans-serif;
	border-radius: 5px;
}

#login-form {
	width: 500px;
	height: 450px;
	background-color: #EEEFF1;
	margin-right: auto;
	margin-left: auto;
	margin-top: 10px;
	padding: 20px;
	text-align: center;
	border: none;
}

#sign-form{
	width: 500px;
	height: 550px;
    background-color: #EEEFF1;
    margin-right: auto;
    margin-left: auto;
    margin-top : 10px;
    padding: 20px;
    text-align: center;
    border: none;
	}

#login-box {
	margin-top: 20px;
}

#text-field {
	font-size: 14px;
	padding: 10px;
	border: none;
	width: 260px;
	margin-bottom: 10px;
}

#text-field2 {
	font-size: 14px;
	padding: 10px;
	border: none;
	width: 260px;
	margin-bottom: 10px;
}

#pass1 {
	font-size: 14px;
	padding: 10px;
	border: none;
	width: 260px;
	margin-bottom: 10px;
}

#pass2 {
	font-size: 14px;
	padding: 10px;
	border: none;
	width: 260px;
	margin-bottom: 10px;
}

.btn {
	width: 260px;
}

#submit-btn {
	font-size: 14px;
	border: none;
	padding: 10px;
	width: 260px;
	background-color: #1BBC9B;
	margin-bottom: 30px;
	color: white;
}

#sign-btn {
	font-size: 14px;
	border: none;
	padding: 10px;
	width: 260px;
	background-color: #1BBC9B;
	margin-bottom: 30px;
	color: white;
	top : 200px;
}

#id-btn{
	position : absolute;
	top : 285px;
	left : 57%;
	width : 70px
}
.links a {
	font-size: 12px;
	color: #9B9B9B;
}

#login_text {
	font-size: 60px;
	font-weight: bold;
	color: #333;
	text-transform: uppercase;
	letter-spacing: 2px;
}

#insert {
	position: absolute;
	top: 337px;
	left: 57%;
}

#keyboard {
	position: absolute;
	top: 360px;
	left: 57%;
	z-index: 1;
}

.green-border {
  border: 2px solid green;
}

.red-border {
  border: 2px solid red;
}

#id_check{
	color : red;
	display : inline;
}
</style>



</head>
<body>

	<header>
		<%@ include file="/jsp/include/top.jsp"%>
	</header>

	<div id="sign-form" class="box">
		<span id="login_text">회원가입</span>
		<div id="login-box">

			<form action="${pageContext.request.contextPath }/sign.do" method="post" id="joinForm">
				<input type="text" name="id" id="text-field" class="box" placeholder="아이디"><br>
				<span id="id_check">중복된 아이디입니다</span><br>
				<input type="button" name="id" id="id-btn" class="btn" value="중복확인">
				<input type="password" name="password" id="pass1" class="box" placeholder="비밀번호" oninput="checkPasswordMatch()"><br>
				<input type="password" name="password2" id="pass2" class="box" placeholder="비밀번호 확인" oninput="checkPasswordMatch()"><br><br>
				<input type="text" name="name" id="text-field" class="box" placeholder="이름"><br> 
				<input type="text" id="text-field" class="box" placeholder="생년월일"><br> 
				<input type="text" id="text-field" class="box" placeholder="휴대번호"><br>
				<input type="text" id="text-field" class="box" placeholder="성별"><br>
				<input type="submit" value="가입하기" id="sign-btn">
			</form>
		</div>

	</div>
</body>
</html>