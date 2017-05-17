function makeAjaxRequest(url , paramsObject , handler){ 
    $.post(url , paramsObject , handler);
}