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
		<%@ include file="/jsp/include/top_board.jsp"%>
	</header>
	
	<input type="text" placeholder="제목:${title }" readonly><br><br>
	<input type="text" placeholder="작성자:${id }" readonly><br><br>
	<input type="text" value="${content }" id="content-box" readonly><br>
	
	<input type="button" value="삭제하기">
	<input type="button" value="수정하기">
	<br>
	
	댓글 : <input type="text" id="comment-input" placeholder="내용을 입력해주세요">
	
	<c:choose>
			<c:when test="${empty sessionScope.user.id}">
				
				<input type="button" value="댓글등록" onclick="showAlert()">
			</c:when>
			<c:otherwise>
				<input type="button" value="댓글등록" onclick="addComment()">
			</c:otherwise>
	</c:choose>
	
	<table border="1" id="comment-table">
	
		<tr>
			<td>아이디</td>
			<td>댓글</td>
			<td>시간</td>
		</tr>
	</table>
	
	<script>
		function addComment() {
			var commentInput = document.getElementById("comment-input");
			var commentTable = document.getElementById("comment-table");
			var newRow = commentTable.insertRow(-1);
			var cell1 = newRow.insertCell(0);
			var cell2 = newRow.insertCell(1);
			var cell3 = newRow.insertCell(2);
			cell1.innerHTML = "${user.id}"; // Replace with actual user ID
			cell2.innerHTML = commentInput.value;
			cell3.innerHTML = new Date().toLocaleString(); // Show current timestamp
			commentInput.value = ""; // Clear the input field after adding the comment
		}
	</script>
</body>
</html>