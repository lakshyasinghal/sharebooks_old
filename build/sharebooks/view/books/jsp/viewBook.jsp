<%@page import="com.sharebooks.user.entities.User"%>
<%@page import="java.util.*"%>


<%
	User user = (User)session.getAttribute("user");
	//String imagesFolderPath = request.getContextPath() + "/view/resources/images/";
	//String profileImagePath = request.getContextPath() + "/view/resources/images/users/lakshya.jpg";
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

	<script type="text/javascript">
		var user = {};
		<% if(user == null){ %>
			location.href = urls.IN;
		<% }
		else{ %>
			user.id = "<%=user.getId()%>";
			user.name = "<%=user.getUsername()%>";
			user.password = "<%=user.getPassword()%>";
		<% } %>

		imagesFolderPath = "<%=imagesFolderPath%>";

	</script>
</head>


<body ng-app="viewBook">


	<div id="mainContainer" class="jumbotron full-height" ng-controller="ViewBookController" ng-init="init()">

		<%@include file="header.jsp"%>


		<div id="bodyContainer">

			<div id="optionsContainer">
				<div id="backButton" ng-click="optionsHandler.goToHomePage()">
					<img src="<%=imagesFolderPath%>/backArrow.png" width="60" height="60">
				</div>

				<div id="showMoreResults" ng-show="optionsHandler.moreResultsLinkShow" ng-click="optionsHandler.showMoreResults()">
					<span>SHOW MORE SIMILAR RESULTS</span>
				</div>
				<div id="showLessResults" ng-show="optionsHandler.lessResultsLinkShow" ng-click="optionsHandler.showLessResults()">
					<span>SHOW LESS RESULTS</span>
				</div>
			</div>



			<div id="resultsContainer">

				<div class="result" ng-repeat="result in resultsHandler.results">
					<div class="bookInfo">

						<div class="container">
						
							<img ng-src="{{'<%=imagesFolderPath%>' + '/books/' + result.book.image}}" width="130" height="180">
					
							<div class="infoPanel">
								<div class="bookName">NAME : {{result.book.name}}</div>
								<div class="authorName">AUTHOR NAME : {{result.book.authorName}}</div>
								<div class="category">CATEGORY : {{result.book.category}}</div>
								<div class="subcategory">SUBCATEGORY : {{result.book.subcategory}}</div>
								<div class="pages">PAGES : {{result.book.pages}}</div>
								<div class="available">AVAILABLE : {{result.book.available == 1 ? "Yes" : "No"}}</div>
							</div>

							<div class="buttonPanel">
								<button class="btn btn-danger buy" ng-click="resultsHandler.proceedToCheckout('buy' , result)" ng-disabled="result.book.available == 0 || result.book.buy == 0">Buy</button>
								<button class="btn btn-danger rent" ng-click="resultsHandler.proceedToCheckout('rent' , result)" ng-disabled="result.book.available == 0 || result.book.rent == 0">Rent</button>
							</div>
						</div>
					</div>

					<div class="userInfo">
						<div class="container">
					
							<div class="infoPanel">
								<div class="userName">NAME : {{result.user.name}}</div>
								<div class="age">AGE : {{calculateAge(result.user.birthday)}}</div>
								<div class="address">ADDRESS : {{result.user.address}}</div>
								<div class="city">CITY : {{result.user.city}}</div>
								<div class="state">STATE : {{result.user.state}}</div>
								<div class="pincode">PINCODE : {{result.user.pincode}}</div>
								<div class="contactNo">CONTACT NO. : {{result.user.mobileNo}}</div>
							</div>

							<div class="buttonPanel">
								<button class="btn btn-lg btn-success" ng-click="viewLocation(result.user)">View Location On Map</button>
							</div>
						</div>
					</div>
				</div>

			</div>

			

		</div>

	</div>


</body>

</html>