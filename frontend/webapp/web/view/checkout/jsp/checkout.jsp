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
	<title>Checkout</title>

	<script type="text/javascript">
		
	</script>

	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/checkout/css/checkout.css">

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/checkout/js/checkoutController.js"></script>

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

	</script>
</head>


<body ng-app="Checkout">


	<div id="mainContainer" class="jumbotron full-height" ng-controller="CheckoutController" ng-init="init()">


		<%@include file="../../include/head.jsp"%>


		<div id="bodyContainer">

			<div id="titleContainer">
				<div id="backButton" ng-click="goToPreviousPage()">
					<img src="<%=imagesFolderPath%>/backArrow.png" width="60" height="60">
				</div>

				<div id="homeButton" ng-click="goHome()">
					<span>HOME</span>
				</div>	

				<span class="pageTitle">CHECKOUT</span>
			</div>	
			
			<div id="checkoutInfoContainer">
				<div id="holdingContainer">
					<div id="bookImageContainer">
						<img ng-src="{{'<%=imagesFolderPath%>' + '/books/' + book.image}}" width="220" height="300">
					</div>
					<div id="detailsContainer">
						<div id="optionDescriptionContainer">{{optionDescription}}</div>

						<div id="rentDetailsContainer" ng-show="option=='rent'">
							<div id="chargesContainer">
								<div class="block1"><span>CHARGES</span></div>
								<div class="block2"><span id="rent">Rs {{rent}} per day</span></div>
							</div>
							<div id="daysContainer">
								<div class="block1"><span>DAYS</span></div>
								<div class="block2">
									<div id="days">
										<span id="daysCount">{{daysCount}}</span>
										<div id="addDays" ng-click="incrementDays()"><span>+<span></div>
										<div id="subtractDays" ng-click="decrementDays()"><span>-</span></div>
									</div>
								</div>
								<div class="block3">
									<span id="daysMessage">{{daysMessage}}</span>
								</div>
							</div>
							<div id="totalAmountContainer">
								<div class="block1"><span>RENT AMOUNT</span></div>
								<div class="block2"><span id="rentAmount">{{rentAmount}}</span></div>
							</div>
						</div>


						<div id="buyDetailsContainer" ng-show="option=='buy'">
							<div id="amountContainer">
								<div class="block1"><span>AMOUNT</span></div>
								<div class="block2"><span id="amount">Rs {{buyAmount}}</span></div>
							</div>
						</div>

						<div id="proceedButtonContainer">
							<button class="btn btn-lg btn-success" ng-click="sendRequest()">PROCEED</button>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>


</body>

</html>