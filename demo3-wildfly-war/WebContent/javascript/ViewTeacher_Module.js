/**
 *  erstellt von Moritz Reindl
 */
"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
  var buttonDelete = document.getElementById('buttonDelete');

  var buttonAddStudie = document.getElementById('buttonAddStudie');
  buttonAddStudie.addEventListener('click', showPopup);

  var buttonPopupClose = document.getElementById('buttonPopupClose');
  buttonPopupClose.addEventListener('click', closePopup);

  var buttonClosePopupAdd = document.getElementById('buttonClosePopupAdd');
  buttonClosePopupAdd.addEventListener('click', closePopupAdd);

  var buttonSaveModul = document.getElementById('buttonSaveModul');
  buttonSaveModul.addEventListener('click', saveModul);

  /**
  *  ab hier generiert durch ChatGPT bis zum Ende des Dokuments
  */
  const buttonPopupClose = document.getElementById("buttonPopupClose");
  const popupDelete = document.getElementById("popupDelete");

  buttonDelete.addEventListener("click", function() {
  popupDelete.classList.add("openPopup");
  });

  buttonPopupClose.addEventListener("click", function() {
     popupDelete.classList.remove("openPopup");
  });
  
}

function openPopup() {
	var popup = document.getElementById("popupDelete");
	popup.style.display = "block";
}

function closePopup() {
	var popup = document.getElementById("popupDelete");
	popup.style.display = "none";
}

function showPopup() {
	var popup = document.getElementById('popup');
	popup.style.display = 'block';
}

function saveModul() {
	var modulInput = document.getElementById('modul');
	var modul = modulInput.value;
	alert('Modul gespeichert: ' + modul);
	var popup = document.getElementById('popup');
	popup.style.display = 'none';
}

function closePopupAdd() {
	var popup = document.getElementById("popup");
	popup.style.display = "none";
}

