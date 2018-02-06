var checkoutApp = angular.module("Checkout" , []);


checkoutApp.controller("CheckoutController" , ["$scope" , "$http" , function($scope , $http){

	$scope.optionDescriptions = {
		"rent" : "GET BOOK ON RENT",
		"buy" : "BUY BOOK"
	};

	$scope.minDays = 7;

	$scope.daysMessages = {
		1 : "Days cannot be less than " + $scope.minDays,
	};

	$scope.option = "";
	$scope.book = null;
	$scope.contact = null;
	$scope.daysCount = "";
	$scope.optionDescription = "";
	$scope.buyAmount = "";
	$scope.rentAmount = "";
	$scope.rent = "";
	
	$scope.daysMessage = "";



	$scope.init = function(){
		try{
			var option;
			var result;
			//$scope.resultsHandler.init();
			if(window.sessionStorage){
				option = sessionStorage.getItem("option");
				result = sessionStorage.getItem("result");
			}
			else{
				throw "Session Storage doesn't exist for the given browser";
			}

			result = JSON.parse(result);

			$scope.option = option;
			$scope.book = result.book;
			$scope.contact = result.user;



			$scope.optionDescription = $scope.optionDescriptions[option];

			$scope.daysCount = $scope.minDays;



			if(option == "buy"){
				$scope.buyAmount = $scope.book.buyAmount;
			}
			else{
				$scope.rent = $scope.book.rent;
				$scope.rentAmount = $scope.rent * $scope.daysCount;
			}
		}
		catch(err){
			$scope.showError("Error in init --- " + err.message);
		}
	};




	$scope.calculateAge = function(birthday){
		try{
			var age = undefined;
			var tokens = birthday.split('/');

			var year = tokens[2];

			var currentDate = new Date();
			var currentYear = currentDate.getFullYear();

			return currentYear - year;
		}
		catch(err){
			console.log("Error in calculateAge --- " + err.message);
		}
	};




	$scope.goToPreviousPage = function(){
		try{
			location.href = urls.VIEW_BOOK;
		}
		catch(err){
			$scope.showError("Error in goToPreviousPage --- " + err.message);
		}
	};




	$scope.goHome = function(){
		try{
			location.href = urls.HOME;
		}
		catch(err){
			$scope.showError("Error in goHomePage --- " + err.message);
		}
	};





	$scope.incrementDays = function(){
		try{
			$scope.cleanUp();
			$scope.daysCount++;
			$scope.rentAmount += $scope.rent; 
		}
		catch(err){
			$scope.showError("Error in incrementDays --- " + err.message);
		}
	};






	$scope.decrementDays = function(){
		try{
			$scope.cleanUp();
			var daysCount = $scope.daysCount - 1;
			if(daysCount < $scope.minDays){
				$scope.daysMessage = $scope.daysMessages["1"];
				return;
			}

			$scope.daysCount--;
			$scope.rentAmount -= $scope.rent; 
		}
		catch(err){
			$scope.showError("Error in decrementDays --- " + err.message);
		}
	};





	$scope.sendRequest = function(){
		try{
			$scope.cleanUp();

			var params = $scope.getSendRequestParams();
			var paramString = getParamString(params);

			$http({
				url: urls.SEND_REQUEST + "?" + paramString,
				method: "GET",
			}).then(function(response){
					$scope.hideLoader();
					var data = response.data;
				} , 
				function(response){
					$scope.hideLoader();
				}
			);
		}
		catch(err){
			$scope.showError("Error in sendRequest --- " + err.message);
		}
	};


	$scope.getSendRequestParams = function(){
		try{
			var params = {};
			params.id = -1;

			if($scope.option == "rent"){
				params.requestType = 1;
			}
			else{
				params.requestType = 2;
			}

			params.requestStatus = 1;

			params.requesterId = user.id;

			params.bookId = $scope.book.id;

			params.targetId = $scope.contact.id;

			return params;
		}
		catch(err){
			$scope.showError("Error in getSendRequestParams --- " + err.message);
		}
	};



	$scope.cleanUp = function(){
		try{
			$scope.daysMessage = "";
		}
		catch(err){
			$scope.showError("Error in cleanUp --- " + err.message);
		}
	};



	$scope.showError = function(errorText){
		try{
			console.log(errorText);
		}
		catch(err){
			alert(errorText);
		}
	};

}]);