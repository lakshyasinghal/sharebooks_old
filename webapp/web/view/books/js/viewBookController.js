var viewBookApp = angular.module("viewBook" , []);


viewBookApp.controller("ViewBookController" , ["$scope" , "$http" , function($scope , $http){


	$scope.init = function(){
		try{
			$scope.resultsHandler.init();
		}
		catch(err){
			console.log("Error in main init --- " + err.message);
		}
	};



	$scope.viewLocationOnMap = function(user){
		try{
			
		}
		catch(err){
			console.log("Error in viewLocationOnMap --- " + err.message);
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



	$scope.optionsHandler = {
		HomePageUrl : urls.HOME ,
		loadMoreResultsLinkShow : true,
		lessResultsLinkShow : false,

		goToHomePage : function(){
			try{
				var self = $scope.optionsHandler;
				location.href = self.HomePageUrl;
			}
			catch(err){
				console.log("Error in goToHomePage in optionsHandler --- " + err.message);
			}
		},


		loadMoreResults : function(){
			try{
				var self = $scope.optionsHandler;

				self.loadMoreResultsLinkShow = false;
				self.lessResultsLinkShow = true;

				$scope.resultsHandler.loadMoreBooks();
			}
			catch(err){
				console.log("Error in loadMoreResults in optionsHandler --- " + err.message);
			}
		},

		showLessResults : function(){
			try{
				var self = $scope.optionsHandler;

				self.lessResultsLinkShow = false;
				self.loadMoreResultsLinkShow = true;

				$scope.resultsHandler.showOriginallySelectedBook();
			}
			catch(err){
				console.log("Error in loadLessResults in optionsHandler --- " + err.message);
			}
		}

	};













	$scope.resultsHandler = {
		selectedBook : null,
		similarBooks : [],
		results : [],
		selectedResult : null,
		similarResults : [],
		results : [],

		init : function(){
			try{
				var self = $scope.resultsHandler;

				if(window.sessionStorage){
					//self.books = JSON.parse(sessionStorage.books);
					self.selectedBook = JSON.parse(sessionStorage.selectedBook);
				}
				else{
					console.log("session object not available in init in resultsHandler");
					return;
				}

				var similarBooks = self.getSimilarBooks(self.selectedBook);


				//getting the similar results for selected book
				//the similar books will also containe the selected book
				for(var i=0; i < similarBooks.length; i++){
					//function for getting result and pushing it into otherResults array
					(function(){
						var book = similarBooks[i];
						var result = {book: book , user: null};
						var userId = book.userId;
						var user = {};


						var params = {"userId" : userId};
						var paramString = getParamString(params);

						//get user by userId
						$http({
							url : urls.GET_USER + "?" + paramString,
							method : "GET"
						}).then(
							function(response){
								data = response.data;
								if(data.success){
									user = data.results[0];

									result.user = user;
									self.similarResults.push(result);

									if(userId == self.selectedBook.userId){
										self.selectedResult = result;
										self.results.push(result);
									}
								}
								else{
									console.log("\nget user request failed\n");
								}
							},
							function(response){
								console.log("Something wrong with the response --- " + response);
							}
						);

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
				self.results = self.similarResults;
			}
			catch(err){
				console.log("Error in loadMoreBooks in resultsHandler --- " + err.message);
			}
		},


		showOriginallySelectedBook : function(){
			try{
				var self = $scope.resultsHandler;

				self.results = [];
				self.results.push(self.selectedResult);
			}
			catch(err){
				console.log("Error in loadOriginallySelectedBook in resultsHandler --- " + err.message);
			}
		},


		getSimilarBooks : function(selectedBook){
			try{
				var similarBooks = [];
				similarBooks.push(selectedBook);
				return similarBooks;
			}
			catch(err){
				console.log("Error in getSimilarBooks in resultsHandler --- " + err.message);
			}
		}
	}



}]);