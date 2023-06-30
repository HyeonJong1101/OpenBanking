<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.modal {
  display: none;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

.modal-button-container {
  text-align: center;
}

</style>

<script>
// Function to display the modal
function showModal() {
  var modal = document.getElementById("modal");
  modal.style.display = "block";
}

// Function to close the modal
function closeModal() {
  var modal = document.getElementById("modal");
  modal.style.display = "none";
}

// Function to submit the form after confirmation
function submitForm() {
  /*var confirmed = confirm("Are you sure you want to register the product?");
  if (confirmed) {
    // Proceed with form submission
    document.getElementById("productForm").submit();
  }*/
  document.getElementById("productForm").submit();
}
</script>

<link rel="stylesheet" href="jsp/style.css">

</head>
<body>

	<header>
		<%@ include file="/jsp/include/top.jsp"%>
		<%@ include file="/jsp/include/top_product_Banker.jsp"%>
	</header>
	
	<br><br><br><br>
	상품등록<br>
	<form id="productForm" action="${pageContext.request.contextPath }/regist.do" method="post">
	상품코드<input type="text" name="product_code"><br>
	상품이름<input type="text" name="product_name"><br>
	상품정보<input type="text" name="product_info"><br>
	<!--  <input type="submit" value="상품등록" onclick="showModal()">-->
	<button type="button" onclick="showModal()">상품등록</button>
	</form>
	
	<div id="modal" class="modal">
  <div class="modal-content">
    <h2>Confirmation</h2>
    <p>Are you sure you want to register the product?</p>
    <div class="modal-button-container">
      <button onclick="submitForm()">Yes</button>
      <button onclick="closeModal()">No</button>
    </div>
  </div>
</div>
</body>
</html>