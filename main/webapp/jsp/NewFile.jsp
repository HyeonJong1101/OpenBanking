<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#searchResult{
		width : 70%;
		height : 500px;
		border : 1px solid red;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('button').click(function(){
			//alert('click')
			//2023-06-21 박스오피스 요청
			$.ajax({
				url : 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
				type : 'get',
				data : {
					key : 'c7da06aee4a5b0eb98e74915c0020ca3',
					targetDt : '20230621',
					itemPerPage : '5'
				},
				success : callback,
				error : function(){
					alert('실패')
				}
			})
		})
	})
	
	let list
	function callback(result){
		list = result.boxOfficeResult.dailyBoxOfficeList
		alert('성공')
		console.log(list)
		for(let i=0; i<list.length; i++){
			let movieInfo = list[i]
			let name = movieInfo.movieNm
			let rank = movieInfo.rank
			
		}
	}
	
	</script>
</head>
<body>
	<div>
		<h2>일별 박스오피스 서비스</h2>
		검색일 : <input type="date" id="searchDate"><button>검색</button>
		<h3>검색결과</h3>
		<div id="searchResult"></div>
	</div>
</body>
</html>