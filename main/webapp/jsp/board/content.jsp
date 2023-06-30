<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="kr.ac.kopo.biz.common.JDBCUtil" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	body {
		font-family: Arial, sans-serif;
	}

	.container {
		margin: 20px;
	}

	.comment-section {
		margin-top: 20px;
		border: 1px solid #ccc;
		padding: 20px;
	}

	.comment-section h2 {
		margin: 0 0 10px;
	}

	.input-field {
		width: 90%;
		padding: 10px;
		margin-bottom: 10px;
		border: 1px solid #ccc;
		border-radius: 4px;
	}
	
	.input-field2 {
		width: 30%;
		padding: 10px;
		margin-bottom: 10px;
		border: 1px solid #ccc;
		border-radius: 4px;
	}
	
	.input-field3 {
		width: 95%;
		height:200px;
		padding: 10px;
		margin-bottom: 10px;
		border: 1px solid #ccc;
		border-radius: 4px;
	}

	.comment-input {
		resize: vertical;
	}

	.buttons {
		margin-top: 10px;
	}

	.buttons input[type="button"] {
		background-color: #4CAF50;
		color: white;
		border: none;
		padding: 10px 20px;
		cursor: pointer;
		margin-right: 10px;
	}

	.buttons input[type="button"]:last-child {
		background-color: #f44336;
	}

	.alert {
		background-color: #f44336;
		color: white;
		padding: 10px;
		margin-bottom: 10px;
		display: none;
	}

	.comment-table {
		width: 100%;
		border-collapse: collapse;
	}

	.comment-table th,
	.comment-table td {
		border: 1px solid #ccc;
		padding: 8px;
	}

	.comment-table th {
		background-color: #f2f2f2;
	}

	.comment-table td:nth-child(1) {
		width: 20%;
	}

	.comment-table td:nth-child(2) {
		width: 60%;
	}

	.comment-table td:nth-child(3) {
		width: 20%;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<header>
		<%@ include file="/jsp/include/top.jsp"%>
		<%@ include file="/jsp/include/top_board.jsp"%>
	</header>
	
	<div class="container">
		
		<div class="comment-section">
			<input type="text" class="input-field2" placeholder="제목:${title }" readonly><br><br>
			<input type="text" class="input-field2" placeholder="작성자:${writer }" readonly><br><br>
			<textarea class="input-field3 comment-input" id="content-box" readonly>${content }</textarea><br>
			<input type="hidden" value="${id }" id="boardid" readonly><br>
			
			<div class="buttons">
				<input type="button" value="삭제하기">
				<input type="button" value="수정하기">
			</div>
			<br>
			<div class="comment-input-container">
				댓글 : <input type="text" id="comment-input" class="input-field" placeholder="내용을 입력해주세요">
				
				<c:choose>
					<c:when test="${empty sessionScope.user.id}">
						<input type="button" value="댓글 등록" onclick="showAlert()">
					</c:when>
					<c:otherwise>
						<input type="button" value="댓글 등록" onclick="addComment()">
					</c:otherwise>
				</c:choose>
			</div>
		<div class="alert" id="alert-message">로그인 해주세요.</div>
			
			<table class="comment-table">
				<thead>
					<tr>
						<th>아이디</th>
						<th>댓글</th>
						<th>시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="comment" items="${commentList }">
						<tr>
							<td>${comment.userID }</td>
							<td>${comment.comment }</td>
							<td>${comment.regDate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
	
	<script>
		function addComment() {
			var commentInput = $("#comment-input").val();
			var boardid = $("#boardid").val();
			var timestamp = new Date().toLocaleString();
			
			var data = {
				"comment": commentInput,
				"boardid": boardid,
				"timestamp": timestamp
			};
			
			$.ajax({
				url: "jsp/board/insertComment.jsp",
				type: "POST",
				data: data,
				success: function(response) {
					var commentTable = $(".comment-table tbody");
					var newRow = $("<tr></tr>");
					var cell1 = $("<td></td>").text("${user.id}");
					var cell2 = $("<td></td>").text($('#comment-input').val());
					var cell3 = $("<td></td>").text(new Date().toLocaleString());
					
					newRow.append(cell1, cell2, cell3);
					commentTable.append(newRow);
					
					
					$("#comment-input").val("");
				},
				error: function(xhr, status, error) {
				}
			});
			
			
		}
		
		function showAlert() {
			$("#alert-message").show();
		}
	</script>
</body>
</html>
