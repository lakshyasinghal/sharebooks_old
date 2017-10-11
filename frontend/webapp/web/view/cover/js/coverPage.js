function init(){
    //cleanInputBoxes();
    putCalendarForDOB();
    signInPanel.init();
    signUpPanel.init();
    windowHandler.init();
}


// function cleanInputBoxes(){
//  $("input").val("");
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

            // $("#signUpPanel").on("click" , ".ui-datepicker", function(e){
            //     e.preventDefault();
            //     e.stopPropagation();
            // });


            $("#signInPanel input").click(function(e){
                e.preventDefault();
                e.stopPropagation();
            });

            $(window).click(function(e){
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
            //$("#signInPanel").css("opacity" , 0);
        }
        catch(err){
            console.log("Error in closeAll in windowHandler --- " + err.message);
        }
    }
};




var signInPanel = {
    url : urls.SIGN_IN ,
    id : "signInPanel",
    parameterIds : ["signIn_username" , "signIn_password"],
    parameterNames : ["username" , "password"],
    buttonId : "signInButton",
    messageContainerId : "signInMessageContainer",

    init : function(){
        var self = window.signInPanel;

        //$("#" + self.id).hover(self.togglePanel);

        $("#" + self.buttonId).click(self.signIn);

        self.setDefaultValues();
    },

    setDefaultValues : function(){
        try{
            var userName = cookieHandler.readCookie("sharebooks_username");

            if(userName){
                $("#signIn_username").val(userName);
            }

            var password = cookieHandler.readCookie("sharebooks_password");

            if(password){
                $("#signIn_password").val(password);
            }
        }
        catch(err){
            console.log("Error in setDefaultValues in signInPanel --- " + err.message);
        }
    },

    cleanUp : function(){
        try{
            var self = window.signInPanel;

            $("#" + self.id + " input").val("");
            $("#" + self.messageContainerId).text("");
        }
        catch(err){
            console.log("Error in cleanUp in signInPanel --- " + err.message);
        }
    },

    signIn : function(e){
        e.preventDefault();
        e.stopPropagation();

        var self = window.signInPanel;

        var data = getDataObject(self.parameterNames , self.parameterIds);

        removeMessages(self.messageContainerId);
        postRequest(self.url , data , self.success , self.failure);
    },


    success : function(data){
        var self = window.signInPanel;
        data = JSON.parse(data);

        if(data.success){
            self.modifyCookies();
            window.location.href = urls.HOME;
        }
        else{
            displayMessage(self.messageContainerId , messages[data.statusCode - 1] , messageColors.ERROR);
        }
    },

    failure : function(){
        var self = window.signInPanel;
    },

    togglePanel : function(){
        try{
            var self = window.signInPanel;

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
            console.log("Error in showPanel in signInPanel --- " + err.message);
        }
    },


    modifyCookies : function(){
        try{
            var userName = cookieHandler.readCookie("sharebooks_username");

            if($("#signIn_username").val() != userName){
                cookieHandler.createCookie("sharebooks_username" , $("#signIn_username").val() , 1 , "sharebooks");
            }

            var password = cookieHandler.readCookie("sharebooks_password");

            if( $("#signIn_password").val() != password){
                cookieHandler.createCookie("sharebooks_password" , $("#signIn_password").val() , 1 , "sharebooks");
            }
        }
        catch(err){
            console.log("Error in modifyCookies in signInPanel --- " + err.message);
        }
    }


};








var signUpPanel = {
    url : urls.SIGN_UP ,
    id : "signUpPanel",
    parameterIds : ["id" , "username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobileNo"],
    parameterNames : ["id" , "username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobileNo"],
    buttonId : "userSignUpButton",
    messageContainerId : "userRegistrationMessageContainer",


    init : function(){
        var self = window.signUpPanel;

        $("#signUpPanel #id").val(-1);

        $("#birthday").click(function(e){
            setTimeout(function(){
                $(".ui-datepicker").on("click" , function(e){
                    e.preventDefault();
                    e.stopPropagation();
                });
            } , 100);
        });

        $("#signUpPanel").click(function(e){
            e.preventDefault();
            e.stopPropagation();
        });

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

        $("#" + self.buttonId).click(self.signUpUser);

        console.log("Registration form button registered");
    },

    cleanUp : function(){
        try{
            var self = window.signUpPanel;

            $("#" + self.id + " input").val("");
            $("#" + self.messageContainerId).text("");
            $("#signUpPanel #id").val(-1);
        }
        catch(err){
            console.log("Error in cleanUp in signUpPanel --- " + err.message);
        }
    },

    validate : function(){
        try{
            var self = window.signUpPanel;
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
            console.log("Error in validate in signUpPanel --- " + err.message);
        }
    },

    signUpUser : function(e){
        e.preventDefault();
        e.stopPropagation();

        var self = window.signUpPanel;
        
        var proceed = self.validate();

        if(!proceed){
            return;
        }

        var data = getDataObject(self.parameterNames , self.parameterIds);

        removeMessages(self.messageContainerId);
        postRequest(self.url , data , self.success , self.failure);
    },


    success : function(data){
        var self = window.signUpPanel;
        data = JSON.parse(data);

        if(data.success){
            displayMessage(self.messageContainerId , messages[data.statusCode - 1] , messageColors.SUCCESS);
        }
        else{
            displayMessage(self.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
        }
    },

    failure : function(){
        var self = window.signUpPanel;
    }
};


window.onload = init;

