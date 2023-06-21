<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="jsp/style.css">

</head>
<body>

	<header>
		<%@ include file="/jsp/include/top.jsp"%>
		<%@ include file="/jsp/include/side.jsp"%>
	</header>

	<section>

		<div id="account-form" class="box">
			<div id="account-sign">
				계좌개설페이지
				<form action="createaccount.do" method="post">

					<div id="account-sign2">
						<select name="goods">
							<option>상품1</option>
							<option>상품2</option>
							<option>상품3</option>
						</select><br><br>
						 <input type="password" id="account_pass" name="password" placeholder="계좌비밀번호" /><br />
						 <input	type="password" id="account_pass2" placeholder="계좌비밀번호확인" /><br>

						 <input type="hidden" name="id" value="${user.id }"/>
					</div>
					<br><br><br>
					<input type="submit" value="계좌개설하기" />
				</form>
			</div>
		</div>
	</section>

</body>
</html>