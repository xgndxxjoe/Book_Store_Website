<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Book Store Administration</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css"> 
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>
	
	<div align="center">
		<hr width="60%">
		<h2 class="pageheading">Quick Actions:</h2>
		<b>
		<a href="create_book">New Book</a> &nbsp; &nbsp;
		<a href="user_form.jsp">New User</a> &nbsp; &nbsp;
		<a href="category_form.jsp">New Category</a> &nbsp; &nbsp;
		<a href="create_customer">New Customer</a>
		</b>
	</div>
	
	<div align="center">
		<hr width="60%">
		<h2 class="pageheading">Recent Sales:</h2>
	</div>
	
	<div align="center">
		<hr width="60%">
		<h2 class="pageheading">Recent Reviews:</h2>
	</div>

	<div align="center">
		<hr width="60%">
		<h2 class="pageheading">Statistics:</h2>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>