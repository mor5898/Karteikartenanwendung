/**
 * 
 */
/*Dieses Dokument wurde erstellt von Riza Dursun*/
"use strict";
document.addEventListener("DOMContentLoaded", init);
function init() {
	document.getElementById("button").addEventListener("click", changeContent);
}

function changeContent() {
	var searchURL = "../ViewStudent_SearchServlet";
	var searchTerm = document.getElementById("searchTerm").value;
	var userId = document.getElementById("userid").value;

	if (searchTerm != null && searchTerm.length > 0) {
		searchURL += "?searchTerm=" + encodeURIComponent(searchTerm);

		if (userId != null && userId.length > 0) {
			searchURL += "&userId=" + encodeURIComponent(userId);

		}
	} else {
		if (userId != null && userId.length > 0) {
			searchURL += "?userId=" + encodeURIComponent(userId);

		}
	}
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.addEventListener("load", function() {

		document.getElementById("hitlist").innerHTML = xmlhttp.responseText;
	});
	xmlhttp.open("GET", searchURL, true);
	xmlhttp.send();
}