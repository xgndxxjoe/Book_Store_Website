<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>
		<c:if test="${category == null}">
			Create New Category
		</c:if>
		<c:if test="${category != null}">
			Edit Category
		</c:if>
	</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>
			<c:if test="${category == null}">
				Create New Category
			</c:if>
			<c:if test="${category != null}">
				Edit Category
			</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${category != null}">
			<form action="update_category" method="post" id="categoryForm">
			<input type="hidden" id="categoryId" name="categoryId" value="${category.categoryId}">
		</c:if>
		<c:if test="${category == null}">
			<form action="create_category" method="post" id="categoryForm"">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Name:</td>
				<td align="left">
					<input type="text" id="name" name="name" size="20" value="${category.name}">
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

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#categoryForm").validate({
			rules: {
				name: "required"
			},
			messages: {
				name: "Please enter catefory name"
			}
		});
		
		$("#cancelButton").click(function() {
			history.go(-1);
		});
	});

</script>
</html>