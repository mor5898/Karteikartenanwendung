/**
 * Datei generiert durch ChatGPT
 */
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
}