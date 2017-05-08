<%@page import="com.sharebooks.books.entities.Book"%>
<%@page import="java.util.*"%>

<html>

<head>
	<title>Sign into sharebooks</title>

	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/home/css/homepage.css">
</head>


<body>

	<div class="jumbotron full-height">

		<div id="headContainer" class="row">
			<div id="profile"></div>
		</div>

		<div id="searchContainer" class="row">
			<div class="col-sm-6 col-md-6">
				<img src="<%=request.getContextPath()%>/view/images/add.png" height="50" width="50" id="addImage">
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" id="searchBooks" name="searchBooks" placeholder="Search Books" class="form-control">
			</div>
		</div>

		<div id="mainContainer" class="row component-border-bottom">
			<div id="categories" class="col-sm-2 col-md-2 full-height component-border-right">
				<div class="category"><span>Science</span></div>
				<div class="category"><span>Fiction</span></div>
				<div class="category"><span>History</span></div>
				<div class="category"><span>Sports</span></div>
				<div class="category"><span>Science</span></div>
				<div class="category"><span>Science</span></div>
				<div class="category"><span>Science</span></div>
			</div>
			<div id="booksContainer" class="col-sm-10 col-md-10 full-height">
				<%
					List<Book> books = (List<Book>)request.getAttribute("books");
					Book book = null;
					int size = books.size();
					for(int i=0; i<size ; i++){
						book = books.get(i);
				%>
				<div class="book">
					<div class="bookImageDiv"></div>
					<div class="bookName"><%=book.getName()%></div>
					<div class="bookAuthorName"><%=book.getAuthorName()%></div>
				</div>

				<%
					}
				%>
			</div>
		</div>

	</div>

</body>

</html>