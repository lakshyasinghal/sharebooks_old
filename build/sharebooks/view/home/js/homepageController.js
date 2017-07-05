var homepageApp = angular.module("homepage" , []);


//controller for homepage

homepageApp.controller("HomePageController" , ['$scope' , '$http' , function($scope , $http){

	
	$scope.categories = [];
	
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
				var self = $scope.windowHandler;
				$(window).click(self.closeAllOpenPanels);
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
	


	$scope.notificationHandler = {
		init : function(){

		},

		closeAll : function(){

		}
	};





	$scope.browsingHandler = {

		init : function(){
			try{
				var self = $scope.browsingHandler;

				$("#browseLink").hover(self.showCategoriesPanel);
				$(".category").hover(self.showSubCategoriesPanel);

			}
			catch(err){
				console.log("Error in init in browsingHandler --- " + err.message);
			}
		},

		showCategoriesPanel : function(e){
			try{
				e.preventDefault();
				e.stopPropagation();

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


				var subcategoriesContainers = $(".subcategoriesContainer");

				for(var i=0 ; i<subcategoriesContainers.length ; i++){
					if(subcategoriesContainers[i].style.display == "block"){
						subcategoriesContainers[i].style.display = "none";
					}
				}

				// $(".subcategoriesContainer").clearQueue();

				// $(".subcategoriesContainer").slideUp("medium" , function(){
				// 	var categoryElem = e.target;
				// 	var subcategoryPanel = categoryElem.getElementsByClassName("subcategoriesContainer")[0];
				// 	$(subcategoryPanel).slideDown("medium");
				// });

				var categoryElem = e.target;
				var subcategoryPanel = categoryElem.getElementsByClassName("subcategoriesContainer")[0];
				$(subcategoryPanel).slideDown("medium");
			}
			catch(err){
				console.log("Error in showSubCategoriesPanel in browsingHandler --- " + err.message);
			}
		},

		closeAll : function(){
			try{
				$(".subcategoriesContainer").hide();
				$("#categoriesContainer").hide();
			}
			catch(err){
				console.log("Error in closeAll in browsingHandler --- " + err.message);
			}
		}

	};
	




	$scope.booksHandler = {

		books : [],

		init : function(){
			try{
				var self = $scope.booksHandler;
				self.getAllBooks();
			}
			catch(err){
				console.log("Error in init in booksHandler --- " + err.message);
			}
		},

		initBookEvents : function(){
			try{
				var self = $scope.booksHandler;
				$(".book").click(self.handleBookClick);
			}
			catch(err){
				console.log("Error in initBookEvents in booksHandler --- " + err.message);
			}
		},



		handleBookClick : function(e){
			try{
				var self = $scope.booksHandler;

				e.preventDefault();
				e.stopPropagation();

				var elem = e.target;
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
				console.log("Get all books request sent");
				getRequest(urls.GET_ALL_BOOKS , null , function(data){
					data = JSON.parse(data);

					if(data.success){
						self.books = data.results;
						self.displayBooks(self.books);
						self.initBookEvents();
					}
					else{
						displayMessage($scope.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
					}
				});
			}
			catch(err){
				showErrorMessage(err.message);
			}
		},

		getBooksByCategory : function(category , subcategory){
			try{
				var self = $scope.booksHandler;
				var books = self.books;
				var tempBooks = [];
				var book = undefined;
				var length = books.length;

				for(var i=0 ; i<length ; i++){
					book = books[i];
					if(book.category === category && book.subcategory === subcategory){
						tempBooks.push(book);
					}
				}

				self.displayBooks(tempBooks);
			}
			catch(err){
				console.log("Error in getBooksByCategory ---- " + err.message);
			}
		},


		//improvement required in this method
		//searching can be done using binary search
		getBooksBySearch : function(){

		},

		displayBooks : function(books){
			try{
				var self = $scope.booksHandler;
				//var books = $scope.books;
				var book;
				var bookDiv;
				var booksContainer = $("#booksContainer")[0];
				var length = books.length;
				booksContainer.innerHTML = "";

				if(length == 0){
					displayMessage("booksContainer" , $scope.messages.NO_BOOKS_FOUND , messageColors.WARNING);
					return;
				}

				for(var i=0 ; i<length ; i++){
					book = books[i];
					bookDiv = self.getBookDiv(book);
					booksContainer.appendChild(bookDiv);
				}
			}
			catch(err){
				console.log("Error in displayBooks -- " + err.message);
			}
		},

		getBookDiv : function(book){
			try{
				var self = $scope.booksHandler;
				var bookDiv = document.createElement("div");
				bookDiv.setAttribute("class" , "book");
				bookDiv.setAttribute("bookId" , book.id);

				var bookImage = createElement("img" , {"src" : $scope.getImagesFolderPath() + "/books/" + book.image , 
				"class" : "bookImage" , "width" : "100" , "height" : "100"});
				
				var bookNameDiv = createElement("div" , {"class" : "bookName"} , book.name);
				
				var auxillarySpan = createElement("span" , null , "By");
				
				var bookAuthorNameDiv = createElement("div" , {"class" : "bookAuthorName"} , book.authorName);
				
				appendChildren(bookDiv , [bookImage , bookNameDiv , auxillarySpan , bookAuthorNameDiv]);

				return bookDiv;
			}
			catch(err){
				console.log("Error in getBookDiv -- " + err.message);
			}
		}
	};
	




	$scope.profileHandler = {
		profileListOptions : ["Sign out" , "Account settings" , "History" , "Messages"],
		profileListHidden : true,

		init : function(){

		},

		toggleProfileList : function(){
			try{
				var self = $scope.profileHandler;

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

		closeAll : function(){
			try{
				var self = $scope.profileHandler;

				self.profileListHidden = true;
			}
			catch(err){
				console.log("Error in toggleProfileList in profileHandler --- " + err.message);
			}
		}
	}


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

		if(data.success){
			$scope.addBookSuccessMessage = data.message;
		}
		else{
			$scope.addBookErrorMessage = data.message;
		}
	};

}]);