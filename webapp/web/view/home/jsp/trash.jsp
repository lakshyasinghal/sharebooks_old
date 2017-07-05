<%
	//List<String> bookCategories = (List<String>)request.getAttribute("bookCategories");
	for(int i=0 ; i<bookCategories.size() ; i++){
            category = bookCategories.get(i);
%>

<%
	}
%>



<%
	List<Book> books = (List<Book>)request.getAttribute("books");
	Book book = null;
	int size = books.size();
	for(int i=0; i<size ; i++){
		book = books.get(i);
%>

<%
	}
%>



<img src="<%=booksImagePath + book.getImage()%>" class="bookImageDiv" width="100" height="120">
<div class="bookName"><%=book.getName()%></div>
<span>By</span>
<div class="bookAuthorName"><%=book.getAuthorName()%></div>

















// $scope.initCategoryDropdown = function(){
	// 	try{
	// 		var categoriesContainer = $("#categories")[0];
	// 		//var length = $scope.categories.length;

	// 		// <div class="dropdown">
	// 		//     <div class="category dropdown-toggle" data-toggle="dropdown">Science<span class="caret"></span></div>
	// 		//     <ul class="dropdown-menu">
	// 		//       <li><a href="#">HTML</a></li>
	// 		//       <li><a href="#">CSS</a></li>
	// 		//       <li><a href="#">JavaScript</a></li>
	// 		//     </ul>
	// 	    // </div>

	// 		for(var category in $scope.categories){
	// 			var dropDownContainerDiv = createElement("div" , {"class" : "dropdown"});


	// 			var dropDownToggleDiv = createElement("div" , {"class" : "category dropdown-toggle" , "data-toggle" : "dropdown"});
	// 			dropDownToggleDiv.innerHTML = category; 
	// 			//+ "<span class=\"caret\"></span>";


	// 			var dropDownList = createElement("ul" , {"class" : "dropdown-menu"});

	// 			var subCategories = $scope.categories[category];
	// 			for(var i=0 ; i<subCategories.length ; i++){
	// 				var dropDownListItem = createElement("li" , null , subCategories[i]);
	// 				dropDownListItem.addEventListener("click" , function(e){
	// 					var elem = e.target;

	// 					var category = elem.parentElement.parentElement.getElementsByClassName("category")[0].innerText;
	// 					var subCategory = elem.innerText;

	// 					$scope.getBooksByCategory(category , subCategory);
	// 				});

	// 				dropDownList.appendChild(dropDownListItem);
	// 			}

	// 			appendChildren(dropDownContainerDiv , [dropDownToggleDiv , dropDownList]);

	// 			categoriesContainer.appendChild(dropDownContainerDiv);
	// 		}


	// 	}	
	// 	catch(err){
	// 		console.log("Error in initCategoryDropdown -- " + err.message);
	// 		//showErrorMessage(err.message);
	// 	}
	// };