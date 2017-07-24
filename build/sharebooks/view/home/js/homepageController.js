 var homepageApp = angular.module("homepage" , []);


//controller for homepage

homepageApp.controller("HomePageController" , ['$scope' , '$http' , function($scope , $http){

	
	$scope.categories = [];
	$scope.showPageLoader = true;
	
	$scope.imagesFolderPath = imagesFolderPath;
	$scope.messages = {
		NO_BOOKS_FOUND : "NO BOOKS FOUND FOR YOUR SEARCH"
	};

	//$scope.books = [];


	$scope.messageContainerId = "";
	//$scope.imagesFolderPath = "";
	
	$scope.init = function(){
		try{
			$scope.booksHandler.init();
			$scope.profileHandler.init();
			$scope.browsingHandler.init();
			$scope.notificationHandler.init();
			$scope.windowHandler.init();
		}
		catch(err){
			console.log("Error in init --- " + err.message);
		}
	};


	$scope.getImagesFolderPath = function(){
		return imagesFolderPath;
	};






	$scope.windowHandler = {
		init : function(){
			try{
				//var self = $scope.windowHandler;
				//$(window).click(self.closeAllOpenPanels);
			}
			catch(err){
				console.log("Error in init in windowHandler --- " + err.message);
			}
		},

		closeAllOpenPanels : function(e){
			try{
				$scope.browsingHandler.closeAll();
				$scope.profileHandler.closeAll();
				$scope.notificationHandler.closeAll();
			}
			catch(err){
				console.log("Error in closeAllOpenPanels in windowHandler --- " + err.message);
			}
		}
	};







	$scope.profileHandler = {
		profileListOptions : ["Sign out"],
		//["Sign out" , "Account settings" , "History" , "Messages"]
		profileListHidden : true,

		init : function(){

		},


		toggleProfileList : function(){
			try{
				var self = $scope.profileHandler;

				event.preventDefault();
				event.stopPropagation();

				$scope.windowHandler.closeAllOpenPanels();

				if(self.profileListHidden){
					self.profileListHidden = false;
				}
				else{
					self.profileListHidden = true;
				}
			}
			catch(err){
				console.log("Error in toggleProfileList in profileHandler --- " + err.message);
			}
		},

		handleRequest : function(target){
			try{
				var self = $scope.profileHandler;
				var option = target.option;
				
				switch(option){
					case "Sign out":
						self.signOut();
						break;
					default :
						break;
				}
			}
			catch(err){
				console.log("Error in handleRequest in profileHandler --- " + err.message);
			}
		},


		signOut : function(){
			try{
				location.href = urls.SIGN_OUT;
			}
			catch(err){
				console.log("Error in signOut in profileHandler --- " + err.message);
			}
		},

		closeAll : function(){
			try{
				var self = $scope.profileHandler;

				// $scope.$apply(function(){
				// 	self.profileListHidden = true;
				// });

				self.profileListHidden = true;
			}
			catch(err){
				console.log("Error in closaAll in profileHandler --- " + err.message);
			}
		}
	};





	


	$scope.notificationHandler = {
		notificationContainerId: "notificationContainer",

		init : function(){

		},

		showNotifications : function(){
			try{
				var self = $scope.notificationHandler;

				event.preventDefault();
				event.stopPropagation();

				if($("#" + self.notificationContainerId).css("display") == "block"){
					$("#" + self.notificationContainerId).fadeToggle("fast");
					return;
				}

				$scope.windowHandler.closeAllOpenPanels();

				$("#" + self.notificationContainerId).fadeToggle("fast");
			}
			catch(err){
				console.log("Error in showNotifications in notificationHandler --- " + err.message);
			}
		},

		getNotifications : function(){
			try{
				var self = $scope.notificationHandler;
				getRequest(urls.GET_NOTIFICATIONS , null , function(){

				});
			}
			catch(err){
				console.log("Error in getNotifications in notificationHandler --- " + err.message);
			}
		},

		closeAll : function(){
			try{
				var self = $scope.notificationHandler;
				$("#" + self.notificationContainerId).hide();
			}
			catch(err){
				console.log("Error in closeAll in notificationHandler --- " + err.message);
			}
		}
	};





	$scope.browsingHandler = {

		init : function(){
			try{
				var self = $scope.browsingHandler;

				//$("#browseLink").click(self.showCategoriesPanel);
				$(".category").click(function(e){
					self.showSubCategoriesPanel(e);
				});

				// $(".category").dblclick(function(){
				// 	alert("Double click");
				// });
				$(".subcategory").click(function(e){
					e.preventDefault();
					e.stopPropagation();
					var elem = e.target;
					var subcategory = $(elem).text().trim();
					var category = $(elem).parents(".category")[0].getElementsByTagName("span")[0].innerText.trim();
					$scope.booksHandler.getBooksByCategory(category , subcategory);
				});
			}
			catch(err){
				console.log("Error in init in browsingHandler --- " + err.message);
			}
		},



		showCategoriesPanel : function(){
			try{
				event.preventDefault();
				event.stopPropagation();

				if($("#categoriesContainer").css("display") == "block"){
					$(".category").css("top" , "0px");
					$("#categoriesContainer").slideUp();
					return;
				}

				$scope.windowHandler.closeAllOpenPanels();

				$("#categoriesContainer").slideDown("medium");
			}
			catch(err){
				console.log("Error in showCategoriesPanel in browsingHandler --- " + err.message);
			}
		},

		showSubCategoriesPanel : function(e){
			try{
				e.preventDefault();
				e.stopPropagation();

				var categoryElem = e.target;
				if(categoryElem.nodeName === "SPAN"){
					categoryElem = categoryElem.parentElement;
				}
				var subcategoryPanel = categoryElem.getElementsByClassName("subcategoriesContainer")[0];


				if($(subcategoryPanel).css("display") == "block"){
					$(subcategoryPanel).slideUp("medium" , function(){
						$(categoryElem).animate({top : "0px"});
					});
					return;
				}

				
				$(".subcategoriesContainer").hide();
				$(".category").css("top" , "0px");

				$(categoryElem).animate({top : "10px"} , function(){
					//$(subcategoryPanel).css("top" , "100%");
					var elems = subcategoryPanel.getElementsByClassName("subcategory");
					for(var i=0,len = elems.length ; i<len ; i++){
						$(elems[i]).css("top" , "0px");
					} 

					$(subcategoryPanel).slideDown("medium");
				});

				//$(subcategoryPanel).slideDown("medium");
				
			}
			catch(err){
				console.log("Error in showSubCategoriesPanel in browsingHandler --- " + err.message);
			}
		},

		closeAll : function(){
			try{
				$(".subcategoriesContainer").hide();
				$(".category").css("top" , "0px");
				$("#categoriesContainer").hide();
			}
			catch(err){
				console.log("Error in closeAll in browsingHandler --- " + err.message);
			}
		}

	};
	




	$scope.booksHandler = {

		books : [],
		selectedBooks : [],
		sortingRequired : false,
		requiredSearchLength : 5,

		init : function(){
			try{
				var self = $scope.booksHandler;
				self.getAllBooks();
			}
			catch(err){
				console.log("Error in init in booksHandler --- " + err.message);
			}
		},


		viewBook : function(e){
			try{
				var self = $scope.booksHandler;

				event.preventDefault();
				event.stopPropagation();


				//get the book id from the bookId attribute
				var elem = event.target;
				var elemClassTokens = elem.getAttribute("class").split(' ');
				var token;
				var elemIsBook = false;

				for(var i=0 ; i<elemClassTokens.length ; i++){
					token = elemClassTokens[i];
					if(token == "book"){
						elemIsBook = true;
						break;
					}
				}

				if(!elemIsBook){
					elem = elem.parentElement;
				}

				var bookId = elem.getAttribute("bookId");
				var book = self.getBookById(bookId);

				if(window.sessionStorage){
					if(book == null){
						sessionStorage.setItem("selectedBook" , null);
					}
					else{
						sessionStorage.setItem("selectedBook" , JSON.stringify(book));
					}
					sessionStorage.setItem("books" , JSON.stringify(self.books));
				}

				window.location.href = urls.VIEW_BOOK;
			}
			catch(err){
				console.log("Error in handleBookClick in booksHandler --- " + err.message);
			}
		},


		getBookById : function(id){
			try{
				var self = $scope.booksHandler;
				var books = self.books;

				for(var i=0 ; i<books.length ; i++){
					if(books[i].id == id){
						return books[i];
					}
				}

				return null;
			}
			catch(err){
				console.log("Error in getBookById in booksHandler --- " + err.message);
			}
		},


		getAllBooks : function(){
			try{
				var self = $scope.booksHandler;

				$http({
					url: urls.GET_ALL_BOOKS,
					method: "GET",
				}).then(function(response){
					$scope.showPageLoader = false;
					self.handleBooksData(response);
					} , function(response){
					$scope.showPageLoader = false;
					console.log("Get books response --- " + response);
				});

			}
			catch(err){
				showErrorMessage(err.message);
			}
		},

		getBooksByCategory : function(category , subcategory){
			try{
				var self = $scope.booksHandler;

				//self.selectedBooks = [];
				$scope.showPageLoader = true;

				var params = {};

				params["category"] = category;
				if(subcategory != undefined){
					params["subcategory"] = subcategory;
				}

				var paramString = getParamString(params);
				
				$http({
					url: urls.FILTER_BY_CATEGORY + "?" + paramString,
					method: "GET",
				}).then(function(response){
					$scope.showPageLoader = false;
					var data = response.data;
					if(data.success){
						var books = data.results;
						self.selectedBooks = books;
					}
					else{
						if(data.statusCode == statusCodes.SESSION_DOES_NOT_EXIST){
							location.reload();
						}
						//displayMessage($scope.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
					}
				} , function(response){
					$scope.showPageLoader = false;
					console.log("Get books response --- " + response);
				});
			}
			catch(err){
				console.log("Error in getBooksByCategory ---- " + err.message);
			}
		},


		handleBooksData : function(response){
			try{
				var self = $scope.booksHandler;
				var data = response.data;
				if(data.success){
					var books = data.results;

					if(self.sortingRequired){
						books.sort(function(book1 , book2){
							if(book1.name < book2.name){
								return -1;
							}
							else if(book1.name > book2.name){
								return 1;
							}
							else
								return 0;
						});
					}

					self.books = books;
					self.selectedBooks = books;
				}
				else{
					if(data.statusCode == statusCodes.SESSION_DOES_NOT_EXIST){
						location.reload();
					}
					//displayMessage($scope.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
				}
			}
			catch(err){
				console.log("Error in handleBooksData ---- " + err.message);
			}
		},


		//improvement required in this method
		//searching can be done using binary search
		getBooksBySearch : function(){
			try{
				var self = $scope.booksHandler;

				var key = event.key;

				if(key != "Enter"){
					return;
				}

				var elem = event.target;
				var searchString = elem.value.trim();

				//return if search string length is less than required length to prevent useless searches
				if(searchString.length < self.requiredSearchLength){
					return;
				}

				$scope.showPageLoader = true;

				var params = {};
				params["searchString"] = searchString;

				var paramString = getParamString(params);
				
				$http({
					url: urls.FILTER_BY_SEARCH + "?" + paramString,
					method: "GET",
				}).then(function(response){
					$scope.showPageLoader = false;
					var data = response.data;
					if(data.success){
						var books = data.results;
						self.selectedBooks = books;
					}
					else{
						if(data.statusCode == statusCodes.SESSION_DOES_NOT_EXIST){
							location.reload();
						}
						//displayMessage($scope.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
					}
				} , function(response){
					$scope.showPageLoader = false;
					console.log("Get books response --- " + response);
				});
			}
			catch(err){
				console.log("Error in getBooksByCategory ---- " + err.message);
			}
		}

	};
	




	


}]);



























































//controller for add book popup in homepage

homepageApp.controller("BookPopupController" , ['$scope' , '$http' , function($scope , $http){
	$scope.params = ["id" , "userId" , "name" , "authorName" , "category" , "subcategory" , "pages" , "image" , "available" , "buy" , "buyAmount" , "rent" , "rentAmount"];
	$scope.selectedCategory = "Science";
	$scope.selectedSubCategory = "";
	$scope.categories = ["Science" , "Computer Science" , "Social Studies" , "Commerce" , "Literature"];
	$scope.subcategories = {
		"Science" : ["Physics" , "Chemistry" , "Maths" , "Biology"],
		"Computer Science" : ["Java" , "Javascript" , "C" , "C++" , "Data Structures & Algorithms" , "HTML" , "Android"],
		"Social Studies" : ["History" , "Geography" , "Economics"],
		"Commerce" : ["Accounts" , "Economics" , "Maths"],
		"Literature" : ["Fiction" , "Romance" , "Thrillers" , "Horror"]
	};
	$scope.addBookSuccessMessage = "";
	$scope.addBookErrorMessage = "";

	$scope.buyChecked = false;
	$scope.rentChecked = false;

	$scope.messageContainer = "addBookMessageContainer";
	
	$scope.init = function(){
		try{
			//$scope.startCategoryDropdown();
			$("#category").click(function(){
				var display = $("#categoriesList")[0].style.display;
				if(display == "block"){
					$("#categoriesList").slideUp("fast");
				}
				else{
					$("#categoriesList").slideDown("fast");
				}
			});

			$("#subcategory").click(function(){
				var display = $("#subcategoriesList")[0].style.display;
				if(display == "block"){
					$("#subcategoriesList").slideUp("fast");
				}
				else{
					$("#subcategoriesList").slideDown("fast");
				}
			});

			$("#addBookButton").click($scope.addBook);
		}
		catch(err){
			console.log("Error in init in BookPopupController --- " + err.message);
		}
	};

	$scope.toggleBuyChecked = function(){
		try{
			if($scope.buyChecked){
				$scope.buyChecked = false;
			}
			else{
				$scope.buyChecked = true;
			}
		}
		catch(err){
			console.log("Error in toggleBuyChecked --- " + err.message);
		}
	};


	$scope.toggleRentChecked = function(){
		try{
			if($scope.rentChecked){
				$scope.rentChecked = false;
			}
			else{
				$scope.rentChecked = true;
			}
		}
		catch(err){
			console.log("Error in toggleRentChecked --- " + err.message);
		}
	};


	$scope.selectCategory = function(){
		try{
			var elem = event.target;
			var category = ($(elem).text()).trim();
			$scope.selectedCategory = category;

			$("#category").val(category);

			$("#categoriesList").hide();
		}
		catch(err){
			console.log("Error in init in selectCategory --- " + err.message);
		}
	};


	$scope.selectSubCategory = function(){
		try{
			var elem = event.target;
			var subcategory = ($(elem).text()).trim();
			$scope.selectedSubCategory = subcategory;

			$("#subcategory").val(subcategory);

			$("#subcategoriesList").hide();
		}
		catch(err){
			console.log("Error in init in selectSubCategory --- " + err.message);
		}
	};

	// $scope.startCategoryDropdown = function(){

	// 	var categoryInput = $("#category")[0];

	// 	$("#categoryDropdown ul li").click(function(event){
	// 		var selectedValue = event.target.innerText;
	// 		categoryInput.value = selectedValue;
	// 	});		
	// };

	$scope.addBook = function(){
		try{
			//$scope.validateFields();
			var paramsObject = $scope.collectParameters();
			var url = urls.ADD_BOOK;

			postRequest(url , paramsObject , $scope.addBookCallBackHandler);
		}
		catch(err){
			console.log("Error in addBook --- " + err.message);
		}
	};

	$scope.validateFields = function(){

	};

	$scope.collectParameters = function(){
		try{
			var params = $scope.params;
			var param;
			var paramsObject = {};

			paramsObject.id = -1;
			paramsObject.userId = user.id;

			console.log("User id : " + paramsObject.userId);

			//starting with 1 because we don't need id and userId
			for(var i=2 ; i<params.length ; i++){
				param = params[i];
				if(param == "buy" || param == "rent"){
					paramsObject[param] = ($("#" + param)[0].checked) ? 1 : 0;
					continue;
				}
				paramsObject[param] = $("#" + param).val();
			}

			//paramsObject.image = "";

			return paramsObject;
		}
		catch(err){
			console.log("Error in collectParameters --- " + err.message);
		}
	};

	$scope.addBookCallBackHandler = function(data){
		data = JSON.parse(data);
		if(data.success){
			//$scope.addBookSuccessMessage = messages[data.statusCode - 1];
			displayMessage($scope.messageContainer , messages[data.statusCode - 1] , messageColors.SUCCESS);
		}
		else{
			displayMessage($scope.messageContainer , messages[data.statusCode - 1] , messageColors.ERROR);
		}
	};

}]);