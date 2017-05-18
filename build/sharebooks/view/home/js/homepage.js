// window.onload = init;



// function init(){
// 	addBookPopup.init();
// }



// var addBookPopup = {
// 	params : ["name" , "authorName" , "category" , "pages" , "imagePath"],

// 	init : function(){
// 		var self = window.addBookPopup;

// 		self.startCategoryDropdown();
// 		$("#addBookButton").click(self.addBook);
// 	},

// 	startCategoryDropdown : function(){
// 		var self = window.addBookPopup;

// 		var categoryInput = $("#category")[0];

// 		$("#categoryDropdown ul li").click(function(event){
// 			var selectedValue = event.target.innerText;
// 			categoryInput.value = selectedValue;
// 		});		
// 	},

// 	addBook : function(){
// 		var self = window.addBookPopup;

// 		var paramsObject = self.collectParameters();
// 		var url = urls.addBookUrl;

// 		$.post(url , paramsObject , self.addBookCallBackHandler);

// 		//makeAjaxRequest(url , paramsObject , self.showSuccessMessage , self.showErrorMessage);
// 	},

// 	collectParameters : function(){
// 		var self = window.addBookPopup;
// 		var params = self.params;

// 		var paramsObject = {};

// 		paramsObject.userId = user.id;

// 		console.log("User id : " + paramsObject.userId);

// 		for(var i=0 ; i<params.length ; i++){
// 			paramsObject[params[i]] = $("#" + params[i]).val();
// 		}

// 		return paramsObject;
// 	},


// 	addBookCallBackHandler : function(data , status){

// 	}
// };