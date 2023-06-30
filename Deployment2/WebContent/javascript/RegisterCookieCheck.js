/**
 * Erstellt von Muhammed Samil Turan
 */
"use strict";

document.addEventListener("DOMContentLoaded", init);

function init()
{
	 if(navigator.cookieEnabled==false)
	 {
      	document.getElementById("check").innerHTML="Cookies sind nicht aktiviert! Bitte aktivieren Sie Cookies!";
     }
}
