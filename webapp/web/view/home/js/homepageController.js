var homepageApp = angular.module("homepage" , []);


homepageApp.controller("HomePageController" , ['$scope' , '$http' , function($scope , $http){

	$scope.addBookSuccessMessage = "";
	$scope.addBookErrorMessage = "";
	
	$scope.init = function(){
		$scope.addBookPopup.init();
	}

	//window.onload = $scope.init;


	$scope.addBookPopup = {
		params : ["name" , "authorName" , "category" , "pages" , "imagePath"],

		init : function(){
			var self = $scope.addBookPopup;

			self.startCategoryDropdown();
			$("#addBookButton").click(self.addBook);
		},

		startCategoryDropdown : function(){
			var self = $scope.addBookPopup;

			var categoryInput = $("#category")[0];

			$("#categoryDropdown ul li").click(function(event){
				var selectedValue = event.target.innerText;
				categoryInput.value = selectedValue;
			});		
		},

		addBook : function(){
			var self = $scope.addBookPopup;

			var paramsObject = self.collectParameters();
			var url = urls.addBookUrl;

			$.post(url , paramsObject , self.addBookCallBackHandler);

			//makeAjaxRequest(url , paramsObject , self.showSuccessMessage , self.showErrorMessage);
		},

		collectParameters : function(){
			var self = $scope.addBookPopup;
			var params = self.params;

			var paramsObject = {};

			paramsObject.userId = user.id;

			console.log("User id : " + paramsObject.userId);

			for(var i=0 ; i<params.length ; i++){
				paramsObject[params[i]] = $("#" + params[i]).val();
			}

			return paramsObject;
		},


		addBookCallBackHandler : function(data , status){
			var self = $scope.addBookPopup;

			if(data.success){
				$scope.addBookSuccessMessage = data.message;
			}
			else{
				$scope.addBookErrorMessage = data.message;
			}
		}
	};

}]);