/**
 * 
 */
"use strict";
document.addEventListener("DOMContentLoaded", init);
function init() {
	document.getElementById("button").addEventListener("click", changeContent);
}

function changeContent() {
	var searchURL = "../View_StudentSearchServlet";
	var searchTerm = document.getElementById("searchTerm").value;
	var userId = document.getElementById("userid").value;
	alert("Das ist die userId: " + userId);
	if (searchTerm != null && searchTerm.length > 0) {
		searchURL += "?searchTerm=" + encodeURIComponent(searchTerm);
			
			if (userId != null && userId.length > 0) {
    			searchURL += "&userId=" + encodeURIComponent(userId);
    			alert(searchURL);
			}
	} else {
		if (userId != null && userId.length > 0) {
    			searchURL += "?userId=" + encodeURIComponent(userId);
    			alert(searchURL);
			}
	}
	var xmlhttp = new XMLHttpRequest();
	// onload new since XHR 2
	//				xmlhttp.onload = function() {
	//					document.getElementById("hitlist").innerHTML = xmlhttp.responseText;
	//				};

	// addEventListener new since XHR 2
	xmlhttp.addEventListener("load", function() {
		alert(xmlhttp.responseText);
		document.getElementById("hitlist").innerHTML = xmlhttp.responseText;
		//alert(xmlhttp.responseText);
		//	document.write(xmlhttp.responseText);
	});
	xmlhttp.open("GET", searchURL, true);
	xmlhttp.send();
}

