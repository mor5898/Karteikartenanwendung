/**
 * Datei generiert durch ChatGPT
 */
"use strict";
/*
function showPopup() {
	var popup = document.getElementById('popup');
	popup.style.display = 'block';
}

function saveStudiengang() {
//	var studiengangInput = document.getElementById('studiengang');
//	var studiengang = studiengangInput.value;
//	alert('Studiengang gespeichert: ' + studiengang);
	var popup = document.getElementById('popup');
	popup.style.display = 'none';
}

function openPopup() {
	var popup = document.getElementById("popupDelete");
	popup.style.display = "block";
}

function closePopup() {
	var popup = document.getElementById("popupDelete");
	popup.style.display = "none";
}

function closePopupAdd() {
	var popup = document.getElementById("popup");
	popup.style.display = "none";
}*/
/*
function showPopup() {
  document.getElementById('popup').classList.add('fade-in');
  document.querySelector('.popup-overlay').style.display = 'block';
}
*/
function closePopupAdd() {
  document.getElementById('popup').classList.remove('fade-in');
  document.getElementById('popup').classList.add('fade-out');
  setTimeout(function() {
    document.querySelector('.popup-overlay').style.display = 'none';
    document.getElementById('popup').classList.remove('fade-out');
  }, 300);
}

function openPopup() {
//  document.getElementById('popupDelete').classList.add('fade-in');
//  document.querySelector('.popup-overlay').style.display = 'block';
	var popup = document.getElementById("popupDelete");
	popup.style.display = "block";
}
/*
function closePopup() {
  document.getElementById('popupDelete').classList.remove('fade-in');
  document.getElementById('popupDelete').classList.add('fade-out');
  setTimeout(function() {
    document.querySelector('.popup-overlay').style.display = 'none';
    document.getElementById('popupDelete').classList.remove('fade-out');
  }, 300);
}
*/
function showPopup() {
  document.getElementById('popup').style.display = 'block';
}

function closePopup() {
  	var popup = document.getElementById("popupDelete");
	popup.style.display = "none";
}

function closePopupAdd() {
  	var popup = document.getElementById("popup");
	popup.style.display = "none";
}