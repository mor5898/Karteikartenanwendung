"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
  var buttonAddStudie = document.getElementById('buttonAddStudie');
  var buttonDelete = document.getElementById('buttonDelete');
  var buttonPopupClose = document.getElementById('buttonPopupClose');
  var buttonClosePopupAdd = document.getElementById('buttonClosePopupAdd');
  var popupDelete = document.getElementById('popupDelete');
  var popup = document.getElementById('popup');

  buttonAddStudie.addEventListener('click', function() {
    openPopup(popup);
  });

  buttonDelete.addEventListener('click', function() {
    openPopup(popupDelete);
  });

  buttonPopupClose.addEventListener('click', function() {
    closePopup(popupDelete);
  });

  buttonClosePopupAdd.addEventListener('click', function() {
    closePopup(popup);
  });
}

function openPopup(popup) {
  popup.style.display = 'block';
}

function closePopup(popup) {
  popup.style.display = 'none';
}
