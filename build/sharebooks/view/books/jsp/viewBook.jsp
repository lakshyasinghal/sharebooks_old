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
	<title>View Book</title>

	<script type="text/javascript">
		
	</script>

	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/books/css/viewBook.css">

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/books/js/viewBookController.js"></script>
</head>


<body ng-app="viewBook">


	<div id="mainContainer" class="jumbotron full-height" ng-controller="ViewBookController" ng-init="init()">

		<%@include file="header.jsp"%>


		<div id="bodyContainer">

			<div id="optionsPanel">
				<div id="backButton" ng-click="optionsPanelHandler.goToHomePage()">
					<img src="<%=imagesFolderPath%>/backArrow.png" width="60" height="60">
				</div>

				<div id="loadMoreResults" ng-show="optionsPanelHandler.loadMoreResultsLinkShow" ng-click="optionsPanelHandler.loadMoreResults()">
					<span>LOAD MORE SIMILAR RESULTS</span>
				</div>
				<div id="showLessResults" ng-show="optionsPanelHandler.lessResultsLinkShow" ng-click="optionsPanelHandler.showLessResults()">
					<span>SHOW LESS RESULTS</span>
				</div>
			</div>



			<div id="resultsContainer">

				<div class="result" ng-repeat="result in resultsHandler.selectedResults track by $index">
					<div class="bookInfo">

						<div class="imageContainer">

						</div>

						<div>{{book.name}}</div>
						<div>{{book.authorName}}</div>
					</div>

					<div class="userInfo">
						
					</div>
				</div>

			</div>

			

		</div>

	</div>


</body>

</html>