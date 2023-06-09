/**
 *  generiert durch ChatGPT
 */

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