<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Categories - Book Store Administration</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2>Categories Management</h2>
		<h3><a href="category_form.jsp">Create New Category</a></h3>
	</div>
	
	<c:if test="${message != null}">
		<div align="center">
		<h4 class="message"><i>${message}</i></h4>
		</div>
	</c:if>
	
	<div align="center">
		<table border = "1" cellpadding = "5">
			<tr>
				<th>Index</th>
				<th>ID</th>		
				<th>Name</th>
				<th>Actions</th>	
			</tr>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${category.categoryId }</td>
					<td>${category.name }</td>
					<td>
						<a href="edit_category?id=${category.categoryId}">Edit</a> &nbsp;&nbsp;
						<a href="javascript:void(0)" class="deleteLink" id="${category.categoryId}">
						Delete
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$(".deleteLink").each(function() {
			$(this).on("click", function() {
				categoryId = $(this).attr("id");
				if(confirm("Are you sure want to delete User with ID: " + categoryId + " ?")){
					window.location = "delete_category?id=" + categoryId;
				}
			});	
		});
	});
</script>
</html>