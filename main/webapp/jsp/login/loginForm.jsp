<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
    function showImage() {
      var image = document.getElementById("keyboard");
      if (image.style.display === "none") {
          image.style.display = "block";
        } else {
          image.style.display = "none";
        }
    }
    </script>
</head>

<style>

.box{
 box-sizing: border-box;
  font-family: 'Noto Sans KR', sans-serif;
  border-radius: 5px;

}

	#login-form{
	width: 500px;
	height: 450px;
    background-color: #EEEFF1;
    margin-right: auto;
    margin-left: auto;
    margin-top : 10px;
    padding: 20px;
    text-align: center;
    border: none;
    
    
	}
	
	#login-box{
	margin-top:20px;
	}
	
	#text-field {
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

.links a {
  font-size: 12px;
  color: #9B9B9B;
}

#login_text{
	font-size : 60px;
	font-weight: bold;
	color: #333;
	text-transform: uppercase;
	letter-spacing: 2px;
}

#insert{
	position : absolute;
	top: 337px;
	left : 57%;
}


#keyboard {
      position: absolute;
      top: 360px;
	  left : 57%;
      z-index: 1;
    }
</style>

<body>

<header>
	<%@ include file="/jsp/include/top.jsp" %>
</header>


	<div id = "login-form" class="box">
	<span id="login_text">Log In</span>
	<div id="login-box">
	
	
	<form action="${pageContext.request.contextPath }/loginProcess.do" method="post">
		<input type="button" value="입력" id="insert" onclick="showImage()">
		<img id="keyboard" src="images/keyboard.png" style="display:none">
		<input type="text" name="id" id="text-field" class="box" placeholder="아이디"><br>
	    <input type="password" name="password" id="text-field" class="box" placeholder="비밀번호"><br>
		<input type="submit" value="로그인" id="submit-btn" class="box">
	</form>
	</div>
	
	<div class="links">
      <a href="#">비밀번호를 잊어버리셨나요?</a>
    </div>
    
    <br>
    <img src="images/kakao.png" class="btn" >
    <img src="images/naver.png" class="btn">
    
	</div>
	

</body>
</html>