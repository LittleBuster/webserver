window.onload = function() {
    if (localStorage.apiKey != "")
        if (localStorage.apiKey != "undefined")
            document.getElementById('key').value = localStorage.apiKey;
}

function loginUser() {
    var $pass = document.getElementById('key').value;
    
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/login?api_key=" + $pass, false);
    xmlHttp.send(null);
    var result = xmlHttp.responseText;
    
    localStorage.apiKey = $pass;
    alert(result);
    document.location.href = "/main";    
}