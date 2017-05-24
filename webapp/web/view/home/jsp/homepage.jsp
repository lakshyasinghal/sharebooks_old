<%@page import="com.sharebooks.books.entities.Book"%>
<%@page import="com.sharebooks.login.entities.User"%>
<%@page import="java.util.*"%>


<%
	User user = (User)session.getAttribute("user");
	String imagesFolderPath = request.getContextPath() + "/view/resources/images/";
	String profileImagePath = request.getContextPath() + "/view/resources/images/users/" + (String)session.getAttribute("profileImage");
%>


<html>

<head>
	<title>Sign into sharebooks</title>

	<script type="text/javascript">
		var user = {};
		user.id = "<%=user.getId()%>";
		user.name = "<%=user.getUserName()%>";
		user.password = "<%=user.getPassword()%>";
	</script>

	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/home/css/homepage.css">

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/home/js/homepageController.js"></script>
</head>


<body ng-app="homepage" ng-controller="HomePageController" ng-init="init()">

	<%@include file="addBookPopup.jsp"%>

	<div class="jumbotron full-height">

		<div id="headContainer" class="row">
			<div id="profile" style="background-image: url('<%=profileImagePath%>')">
				<div id="profileList" class="absolute">	
					<table id="profileListTable">
						<tr>
							<td>Sign out</td>
						</tr>
						<tr>
							<td>Account Settings</td>
						</tr>
						<tr>
							<td>History</td>
						</tr>
						<tr>
							<td>Messages</td>
						</tr>
						<tr>
							<td>Notifications</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div id="searchContainer" class="row">
			<div class="col-sm-6 col-md-6">
				<img src="<%=imagesFolderPath%>/add.png" height="50" width="50" class="pointer" id="addImage" data-toggle="modal" data-target="#addBookPopup">
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" id="searchBooks" name="searchBooks" placeholder="Search Books" class="form-control">
			</div>
		</div>

		<div id="mainContainer" class="row component-border-bottom">
			<div id="categories" class="col-sm-2 col-md-2 full-height component-border-right">
				<%
					//List<String> bookCategories = (List<String>)request.getAttribute("bookCategories");
					for(int i=0 ; i<bookCategories.size() ; i++){
                            category = bookCategories.get(i);
				%>
				<div class="category"><span><%=category%></span></div>
				<%
					}
				%>
				<!-- <div class="category"><span>Fiction</span></div>
				<div class="category"><span>History</span></div>
				<div class="category"><span>Sports</span></div>
				<div class="category"><span>Science</span></div>
				<div class="category"><span>Science</span></div>
				<div class="category"><span>Science</span></div> -->
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