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
		moreResultsLinkShow : true,
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


		showMoreResults : function(){
			try{
				var self = $scope.optionsHandler;

				self.moreResultsLinkShow = false;
				self.lessResultsLinkShow = true;

				$scope.resultsHandler.showMoreResults();
			}
			catch(err){
				console.log("Error in showMoreResults in optionsHandler --- " + err.message);
			}
		},

		showLessResults : function(){
			try{
				var self = $scope.optionsHandler;

				self.lessResultsLinkShow = false;
				self.moreResultsLinkShow = true;

				$scope.resultsHandler.showLessResults();
			}
			catch(err){
				console.log("Error in showLessResults in optionsHandler --- " + err.message);
			}
		}

	};













	$scope.resultsHandler = {
		selectedBook : null,
		similarBooks : [],
		selectedResult : null,
		similarResults : [],
		results : [],
		getSimilarBooksRequestCompleted : false,

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

				self.getSimilarBooks(self.selectedBook);

			}
			catch(err){
				console.log("Error in init in resultsHandler --- " + err.message);
			}
		},


		getSimilarResults : function(){
			try{
				var self = $scope.resultsHandler;
				var similarBooks = self.similarBooks;
				//getting the similar results for selected book
				//the similar books will also containe the selected book
				for(var i=0; i < self.similarBooks.length; i++){
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
										self.results.push(self.selectedResult);
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

			}
		},



		showMoreResults : function(){
			try{
				var self = $scope.resultsHandler;
				self.results = self.similarResults;
			}
			catch(err){
				console.log("Error in loadMoreBooks in resultsHandler --- " + err.message);
			}
		},


		showLessResults : function(){
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
				var self = $scope.resultsHandler;
				var bookName = selectedBook.name;

				var params = {"bookName" : bookName};
				var paramString = getParamString(params);

				$http({
					url : urls.GET_SIMILAR_BOOKS + "?" + paramString,
					method : "GET",
					timeout : 3000
				}).then(
					function(response){
						data = response.data;
						if(data.success){
							self.similarBooks = data.results;
							self.getSimilarResults();
						}
						else{
							console.log("\nget similar results request failed\n");
						}
					},
					function(response){
						console.log("Something wrong with the response --- " + response);
					}
				);

				return ;
			}
			catch(err){
				console.log("Error in getSimilarBooks in resultsHandler --- " + err.message);
			}
		},


		proceedToCheckout : function(option , result){
			try{
				var self = $scope.resultsHandler;

				sessionStorage.setItem("option" , option);
				sessionStorage.setItem("result" , JSON.stringify(result));

				window.location.href = urls.CHECKOUT; 
			}
			catch(err){
				console.log("Error in proceedToCheckout --- " + err.message);
			}
		}

	}



}]);