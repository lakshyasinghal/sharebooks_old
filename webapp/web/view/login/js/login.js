window.onload = init;


function init(){
	cleanInputBoxes();
	putCalendarForDOB();
}


function cleanInputBoxes(){
	$("input").val("");
}


function putCalendarForDOB(){
	$( "#birthday" ).datepicker({
        dateFormat: 'dd/mm/yy',
        minDate: '-100y',
        maxDate: '-1d',
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true
    });
}



