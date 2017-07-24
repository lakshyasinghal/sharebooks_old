var viewBookApp = angular.module("viewBook" , []);


viewBookApp.controller("ViewBookController" , ["$scope" , "$http" , function($scope , $http){


	$scope.init = function(){
		try{
			$scope.resultsHandler.init();
		}
		catch(err){
			console.log("Error in main init --- " + err.message);
		}
	}



	$scope.optionsPanelHandler = {
		HomePageUrl : urls.HOME ,
		loadMoreResultsLinkShow : true,
		lessResultsLinkShow : false,

		goToHomePage : function(){
			try{
				var self = $scope.optionsPanelHandler;
				location.href = self.HomePageUrl;
			}
			catch(err){
				console.log("Error in goToHomePage in optionsPanelHandler --- " + err.message);
			}
		},


		loadMoreResults : function(){
			try{
				var self = $scope.optionsPanelHandler;

				self.loadMoreResultsLinkShow = false;
				self.lessResultsLinkShow = true;

				$scope.resultsHandler.loadMoreBooks();
			}
			catch(err){
				console.log("Error in loadMoreResults in optionsPanelHandler --- " + err.message);
			}
		},

		showLessResults : function(){
			try{
				var self = $scope.optionsPanelHandler;

				self.lessResultsLinkShow = false;
				self.loadMoreResultsLinkShow = true;

				$scope.resultsHandler.showOriginallySelectedBook();
			}
			catch(err){
				console.log("Error in loadLessResults in optionsPanelHandler --- " + err.message);
			}
		},

	}


	$scope.resultsHandler = {
		books : [],
		selectedBook : null,
		results : [],
		selectedResult : null,
		selectedResults : [],
		otherResults : [],

		init : function(){
			try{
				var self = $scope.resultsHandler;

				if(window.sessionStorage){
					self.books = JSON.parse(sessionStorage.books);
					self.selectedBook = JSON.parse(sessionStorage.selectedBook);
				}
				else{
					console.log("session object not available in init in resultsHandler");
				}

				var selectedBooks = self.getSimilarBooks(self.books , self.selectedBook);



				//getting the similar results for selected book
				for(var i=0 ; i<selectedBooks.length ; i++){
					//function for getting result and pushing it into otherResults array
					(function(){
						var book = selectedBooks[i];
						var result = {book: book , user: null};
						var userId = book.userId;
						var user = {};
						//get user by userId
						postRequest(urls.GET_USER , {"userId" : userId} , function(data){
							data = JSON.parse(data);
							if(data.success){
								user = data.results[0];

								result.user = user;
								self.otherResults.push(result);

								if(userId == self.selectedBook.userId){
									self.selectedResult = result;
									self.selectedResults.push(self.selectedResult);
								}
							}
							else{

							}
						});
					})();

				}
			}
			catch(err){
				console.log("Error in init in resultsHandler --- " + err.message);
			}
		},



		loadMoreBooks : function(){
			try{
				var self = $scope.resultsHandler;

				self.selectedResults = self.otherResults;
			}
			catch(err){
				console.log("Error in loadMoreBooks in resultsHandler --- " + err.message);
			}
		},


		showOriginallySelectedBook : function(){
			try{
				var self = $scope.resultsHandler;

				self.selectedResults = [];
				self.selectedResults.push(self.selectedResult);
			}
			catch(err){
				console.log("Error in loadOriginallySelectedBook in resultsHandler --- " + err.message);
			}
		},


		getSimilarBooks : function(books , selectedBook){
			try{
				var selectedBooks = [];
				return books;
			}
			catch(err){
				console.log("Error in getSimilarBooks in resultsHandler --- " + err.message);
			}
		}
	}



}]);