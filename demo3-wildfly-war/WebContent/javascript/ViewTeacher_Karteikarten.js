/**
 * erstellt durch Samil Turan
 */
"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
  var buttonDelete = document.getElementById('buttonDelete');
  var buttonPopupClose = document.getElementById('buttonPopupClose');
  var buttonPopupOpen = document.getElementById('buttonPopupOpen'); 
  var popupDelete = document.getElementById('popupDelete');

  buttonPopupClose.addEventListener('click', function() {
    closePopup(popupDelete);
  });

  buttonDelete.addEventListener('click', function() {
    openPopup(popupDelete);
  });

  buttonPopupOpen.addEventListener('click', function() {
    closePopup(popupDelete);
  });
}

function openPopup(popup) {
	popup.style.display = "block";
}

function closePopup(popup) {
	popup.style.display = "none";
}
