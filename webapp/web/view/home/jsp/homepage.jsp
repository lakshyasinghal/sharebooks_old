<%@page import="com.sharebooks.books.entities.Book"%>
<%@page import="com.sharebooks.user.entities.User"%>
<%@page import="java.util.*"%>


<%
	User user = (User)session.getAttribute("user");
	//String imagesFolderPath = request.getContextPath() + "/view/resources/images/";
	String profileImagePath = request.getContextPath() + "/view/resources/images/users/lakshya.jpg";
	String imagesFolderPath = request.getContextPath() + "/view/resources/images";
%>


<html>

<head>
	<title>Sign into sharebooks</title>

	<script type="text/javascript">
		var user = {};
		user.id = "<%=user.getId()%>";
		user.name = "<%=user.getUsername()%>";
		user.password = "<%=user.getPassword()%>";


		imagesFolderPath = "<%=imagesFolderPath%>";
	</script>

	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/home/css/homepage.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/home/js/homepage.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/home/js/homepageController.js"></script>
</head>


<body ng-app="homepage" >

	<%@include file="addBookPopup.jsp"%>

	<div id="mainContainer" class="jumbotron full-height" ng-controller="HomePageController" ng-init="init()">

		<div id="headContainer" class="row">
			<div id="appTitle" class="vertical-center">
				SHAREBOOKS
			</div>

			<div id="browseLink" class="vertical-center horizontal-center pointer">
				<span>Browse</span>
				<img src="<%=imagesFolderPath%>/downArrow.png" height="15" width="15" >
			</div>


			<%@include file="categories.jsp"%>


			<div id="addBookLink" class="pointer vertical-center" ng-click="" data-toggle="modal" data-target="#addBookPopup">
				<img src="<%=imagesFolderPath%>/add.png" height="20" width="20" id="addImage" class="pointer">
				<span>ADD BOOK</span> 
			</div>

			<div id="notificationIcon" class="pointer vertical-center" ng-click="notificationHandler.showNotifications()">
				<img src="<%=imagesFolderPath%>/notificationIcon.png" width="30" height="30">

				<div id="notificationContainer" class="horizontal-center">
					<img src="<%=imagesFolderPath%>/pointer.ico" width="15" height="15" class="horizontal-center">
				</div>
			</div>

			<div id="profile" class="pointer vertical-center" ng-click="profileHandler.toggleProfileList()">
				<img src="<%=imagesFolderPath%>/userProfile.png" width="40" height="40">

				<div id="profileList" ng-hide="profileHandler.profileListHidden" class="absolute">	
					<table id="profileListTable">
						<tr ng-repeat="option in profileHandler.profileListOptions">
							<td>{{option}}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>




		<div id="searchContainer" class="row">
			<div class="col-sm-12 col-md-12">
				<input type="text" id="searchBooks" name="searchBooks" placeholder="Search books by name, author" class="form-control">
			</div>
		</div>



		

		<div id="bodyContainer" class="row component-border-bottom">
			<!-- <div id="categories" class="col-sm-2 col-md-2 full-height component-border-right">
				<div class="category"><span>SCIENCE</span></div>
			</div> -->
			
			<div id="booksContainer" class="col-sm-12 col-md-12 full-height">
				<!-- <div class="book" ng-repeat="book in books">
					<img ng-src="{{getImagesFolderPath() + '/books/' + book.image}}" class="bookImageDiv" width="100" height="120">
					<div class="bookName">{{book.name}}</div>
					<span>By</span>
					<div class="bookAuthorName">{{book.authorName}}</div>
				</div> -->
				<!-- <div class="message">No Results Found for your search</div> -->
			</div>
		</div>

	</div>

</body>

</html>