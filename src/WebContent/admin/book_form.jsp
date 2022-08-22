<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create New Book</title>
	
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery-ui.min.css">
	
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class = "pageheading">
			<c:if test="${book != null}">
				Edit Book
			</c:if>
			<c:if test="${book == null}">
				Create New Book
			</c:if>
		</h2>
	</div>
	<div align ="center">
		<c:if test="${book != null}">
			<form action="update_book" method="post" id="bookForm">
			<input type="hidden" id="bookId" name="bookId" value="${book.bookId}">
		</c:if>
		<c:if test="${book == null}">
			<form action="create_book" method="post" id="bookForm">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Category:</td>
				<td>
					<select name="category">
						<c:forEach items="${listCategory}" var="category">
							<option value = "${category.categoryId}">
								${category.name}
							</option> 
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" id="title" name="title"
					size="20" value="${book.title}"></td>
			</tr>
			<tr>
				<td align="right">Author:</td>
				<td align="left"><input type="text" id="author"
					name="author" size="20" value="${book.author}"></td>
			</tr>
			<tr>
				<td align="right">ISBN:</td>
				<td align="left"><input type="text" id="isbn"
					name="isbn" size="20" value="${book.isbn}"></td>
			</tr>
			<tr>
				<td align="right">Publish Date:</td>
				<td align="left"><input type="text" id="publishDate"
					name="publishDate" size="20" value="${book.publishDate}"></td>
			</tr>
			<tr>
				<td align="right">Book Image:</td>
				<td align="left">
					<input type="file" id="bookImage" name="bookImage" size="20" /><br/>
					<img alt="" src="" id = "thumbnail" style="width: 20%; margin-top: 10px">
				</td>
					
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price"
					name="price" size="20" value="${book.price}"></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
					<textarea rows="5" cols="50" name="description" is="description"></textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id="cancelButton">Cancel</button>
					<button type="submit">Save</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
<script type="text/javascript">

	$(document).ready(function() {
		$("#publishDate").datepicker();
		$("#bookImage").change(function() {
			showImageThumbnail(this);
		});
		
		$("#userForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				
				fullname: "required",
				password: "required",
			},
			
			messages: {
				email: {
					required: "Please enter email",
					email: "Please enter an valid email address"
				},
				
				fullname: "Please enter full name",
				password: "Please enter password",
			}
		});
		
		$("#cancelButton").click(function() {
			history.go(-1);
		});
	});
	
	//Hien thi anh cho nguoi dung chon
	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$("#thumbnail").attr("src", e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
</script>
</html>