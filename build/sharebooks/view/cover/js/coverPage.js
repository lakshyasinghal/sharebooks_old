function init(){
	//cleanInputBoxes();
	putCalendarForDOB();
    signInForm.init();
    registrationForm.init();
    windowHandler.init();
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



var windowHandler = {
    ids : ["signInPanel" , "signUpPanel"],
    selectedClass : "panel",

    init : function(){
        try{
            var self = windowHandler;

            $("#signUpPanel input").click(function(e){
                e.preventDefault();
                e.stopPropagation();
            });

            $("#signInPanel input").click(function(e){
                e.preventDefault();
                e.stopPropagation();
            });

            $(window).click(function(){
                self.closeAll();
            });
        }
        catch(err){
            console.log("Error in init in windowHandler --- " + err.message);
        }
    },

    closeAll : function(){
        try{
            var self = windowHandler;

            $("#signUpPanel").hide();
            $("#signInPanel").css("opacity" , 0);
        }
        catch(err){
            console.log("Error in closeAll in windowHandler --- " + err.message);
        }
    }
};




var signInForm = {
    url : urls.SIGN_IN ,
    id : "signInPanel",
    parameterIds : ["signIn_username" , "signIn_password"],
    parameterNames : ["username" , "password"],
    buttonId : "signInButton",
    messageContainerId : "signInMessageContainer",

    init : function(){
        var self = window.signInForm;

        $("#" + self.id).hover(self.togglePanel);
        //$("#" + self.id).mouseout(self.hidePanel);

        $("#" + self.buttonId).click(self.signIn);
        console.log("Sign in form button registered");
    },

    cleanUp : function(){
        try{
            var self = window.signInForm;

            $("#" + self.id + " input").val("");
            $("#" + self.messageContainerId).text("");
        }
        catch(err){
            console.log("Error in cleanUp in signInForm --- " + err.message);
        }
    },

    signIn : function(e){
        e.preventDefault();
        e.stopPropagation();

        var self = window.signInForm;

        var data = getDataObject(self.parameterNames , self.parameterIds);

        removeMessages(self.messageContainerId);
        postRequest(self.url , data , self.success , self.failure);
    },


    success : function(data){
        var self = window.signInForm;
        data = JSON.parse(data);

        if(data.success){
            window.location.href = urls.HOME;
        }
        else{
            displayMessage(self.messageContainerId , messages[data.statusCode - 1] , messageColors.ERROR);
        }
    },

    failure : function(){
        var self = window.signInForm;
    },

    togglePanel : function(){
        try{
            var self = window.signInForm;

            var opacity = $("#" + self.id).css("opacity");

            if(opacity == 0){
                self.cleanUp();
                $("#" + self.id).animate({opacity : 1});
            }
            else{
                $("#" + self.id).animate({opacity : 0});
            }
            
        }
        catch(err){
            console.log("Error in showPanel in signInForm --- " + err.message);
        }
    },

    // showPanel : function(){
    //     try{
    //         var self = window.signInForm;

    //         $("#" + self.id).animate({opacity : 1});
    //     }
    //     catch(err){
    //         console.log("Error in showPanel in signInForm --- " + err.message);
    //     }
    // },

    // hidePanel : function(){
    //     try{
    //         var self = window.signInForm;

    //         $("#" + self.id).css("opacity" , 0);
    //     }
    //     catch(err){
    //         console.log("Error in hidePanel in signInForm --- " + err.message);
    //     }
    // }
}






var registrationForm = {
    url : urls.SIGN_UP ,
    id : "signUpPanel",
    parameterIds : [ "username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobileNo"],
    parameterNames : ["username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobileNo"],
    buttonId : "userRegistrationButton",
    messageContainerId : "userRegistrationMessageContainer",


    init : function(){
        var self = window.registrationForm;

        $("#signUpButton").click(function(e){
            e.preventDefault();
            e.stopPropagation();

            self.cleanUp();

            var display = $("#" + self.id).css("display");

            if(display == "none"){
                $("#" + self.id).show("slide", { direction: "right" }, 500);
            }
            else{
                $("#" + self.id).hide();
            }
        });

        $("#" + self.buttonId).click(self.registerUser);

        console.log("Registration form button registered");
    },

    cleanUp : function(){
        try{
            var self = window.registrationForm;

            $("#" + self.id + " input").val("");
            $("#" + self.messageContainerId).text("");
        }
        catch(err){
            console.log("Error in cleanUp in registrationForm --- " + err.message);
        }
    },

    validate : function(){
        try{
            var self = window.registrationForm;
            var proceed = true;
            //var inputFields = $("#" + self.id + " input");

            $("#" + self.id + " input").each(function(index , element){
                if($(element).val() == ""){
                    proceed = false;
                }
            });

            displayMessage(self.messageContainerId , validationMessages.EMPTY_FIELDS , messageColors.WARNING);

            return proceed;
        }
        catch(err){
            console.log("Error in validate in registrationForm --- " + err.message);
        }
    },

    registerUser : function(e){
        e.preventDefault();
        e.stopPropagation();

        var self = window.registrationForm;
        
        var proceed = self.validate();

        if(!proceed){
            return;
        }

        var data = getDataObject(self.parameterNames , self.parameterIds);

        removeMessages(self.messageContainerId);
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



