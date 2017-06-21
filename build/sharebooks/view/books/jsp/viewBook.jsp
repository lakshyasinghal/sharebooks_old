<%@page import="com.sharebooks.books.entities.Book"%>
<%@page import="com.sharebooks.user.entities.User"%>
<%@page import="java.util.*"%>


<html>

<head>
	<title>View Book</title>

	<script type="text/javascript">
		
	</script>

	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/books/css/ViewBook.css">

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/books/js/viewBookController.js"></script>
</head>


<body ng-app="viewBook">

	<!-- <div class="jumbotron full-height" ng-controller="ViewBookController" ng-init="init()"></div> -->
	<h1>WELCOME TO VIEW BOOK PAGE</h1>

</body>

</html>