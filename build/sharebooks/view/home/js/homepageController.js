var homepageApp = angular.module("homepage" , []);


//controller for homepage

homepageApp.controller("HomePageController" , ['$scope' , '$http' , function($scope , $http){

	$scope.profileListOptions = ["Sign out" , "Account settings" , "History" , "Messages"];
	$scope.profileListHidden = true;
	
	$scope.init = function(){
		//$scope.profileIcon.init();
	};

	$scope.toggleProfileList = function(){
		if($scope.profileListHidden){
			$scope.profileListHidden = false;
		}
		else{
			$scope.profileListHidden = true;
		}
	};


}]);






//controller for add book popup in homepage

homepageApp.controller("BookPopupController" , ['$scope' , '$http' , function($scope , $http){
	$scope.params = ["name" , "authorName" , "category" , "pages" , "imagePath"];
	$scope.addBookSuccessMessage = "";
	$scope.addBookErrorMessage = "";
	
	$scope.init = function(){
		$scope.startCategoryDropdown();
		$("#addBookButton").click($scope.addBook);
	};

	$scope.startCategoryDropdown = function(){

		var categoryInput = $("#category")[0];

		$("#categoryDropdown ul li").click(function(event){
			var selectedValue = event.target.innerText;
			categoryInput.value = selectedValue;
		});		
	};

	$scope.addBook = function(){

		var paramsObject = $scope.collectParameters();
		var url = urls.addBookUrl;

		$.post(url , paramsObject , $scope.addBookCallBackHandler);
	};

	$scope.collectParameters = function(){
		var params = $scope.params;

		var paramsObject = {};

		paramsObject.userId = user.id;

		console.log("User id : " + paramsObject.userId);

		for(var i=0 ; i<params.length ; i++){
			paramsObject[params[i]] = $("#" + params[i]).val();
		}

		return paramsObject;
	};

	$scope.addBookCallBackHandler = function(data , status){

		if(data.success){
			$scope.addBookSuccessMessage = data.message;
		}
		else{
			$scope.addBookErrorMessage = data.message;
		}
	};

}]);