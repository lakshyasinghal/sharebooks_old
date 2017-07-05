function init(){
	//cleanInputBoxes();
	putCalendarForDOB();
    signInForm.init();
    registrationForm.init();
}


// function cleanInputBoxes(){
// 	$("input").val("");
// }


function putCalendarForDOB(){
    console.log("Inside calender function");
	$( "#birthday" ).datepicker({
        dateFormat: 'dd/mm/yy',
        minDate: '-100y',
        maxDate: '-1d',
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true
    });
}



var messageContainerIds = ["signInMessageContainer" , "registerUserMessageContainer"];



var signInForm = {
    url : urls.SIGNIN ,
    parameterIds : ["signIn_username" , "signIn_password"],
    parameterNames : ["username" , "password"],
    buttonId : "signInButton",
    messageContainerId : "signInMessageContainer",

    init : function(){
        var self = window.signInForm;
        var buttonId = self.buttonId;

        var button = document.getElementById(buttonId);
        button.addEventListener("click" , self.signIn);
        
        console.log("Sign in form button registered");
    },

    signIn : function(e){
        e.preventDefault();
        e.stopPropagation();

        var self = window.signInForm;

        var data = getDataObject(self.parameterNames , self.parameterIds);

        removeMessages(messageContainerIds);
        postRequest(self.url , data , self.success , self.failure);
    },


    success : function(data){
        var self = window.signInForm;
        data = JSON.parse(data);

        if(data.success){
            window.location.href = urls.HOME;
        }
        else{
            displayMessage(self.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
        }
    },

    failure : function(){
        var self = window.signInForm;
    }
}






var registrationForm = {
    url : urls.ADD_USER ,
    parameterIds : ["username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobileNo"],
    parameterNames : ["username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobileNo"],
    buttonId : "registerUserButton",
    messageContainerId : "registerUserMessageContainer",

    init : function(){
        var self = window.registrationForm;
        var buttonId = self.buttonId;

        var button = document.getElementById(buttonId);
        button.addEventListener("click" , self.registerUser);

        console.log("Registration form button registered");
    },

    registerUser : function(e){
        e.preventDefault();
        e.stopPropagation();

        var self = window.registrationForm;

        var data = getDataObject(self.parameterNames , self.parameterIds);

        removeMessages(messageContainerIds);
        postRequest(self.url , data , self.success , self.failure);
    },


    success : function(data){
        var self = window.registrationForm;
        data = JSON.parse(data);

        if(data.success){
            displayMessage(self.messageContainerId , messages[data.statusCode - 1] , messageColors.SUCCESS);
        }
        else{
            displayMessage(self.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
        }
    },

    failure : function(){
        var self = window.registrationForm;
    }
}


window.onload = init;



