/*Erstellt von Fatih Doruk*/
	"use strict";
	document.addEventListener("DOMContentLoaded", init);
	function init()
	{
		let begruendungButton = document.getElementById("begruendungButton");
		begruendungButton.addEventListener("click", toggleBegruendung);
		
		
		let antwortA = document.getElementById("antwortA");
		antwortA.addEventListener("click", changeAntworten);
		
		let antwortB = document.getElementById("antwortB");
		antwortB.addEventListener("click", changeAntworten);
		
		let antwortC = document.getElementById("antwortC");
		antwortC.addEventListener("click", changeAntworten);
		
		let antwortD = document.getElementById("antwortD");
		antwortD.addEventListener("click", changeAntworten);
		
		let karteikartenLänge = document.getElementById("length").value;
		let aktuelleKarteikarte = document.getElementById("count").value;
		console.log(karteikartenLänge);
		console.log(aktuelleKarteikarte);
		if(karteikartenLänge === aktuelleKarteikarte)
		{
			let naechsteFrageButton = document.getElementById("naechsteFrage");
			naechsteFrageButton.disabled = true;
			naechsteFrageButton.className = "disabledButton";
			alert("Letzte Karteikarte!");
		}
	}
	function toggleBegruendung()
	{
		let begruendung = document.getElementById("begruendung");
		let hidden = begruendung.getAttribute("hidden");
		let begruendungButton = document.getElementById("begruendungButton");
		if(hidden)
		{
			begruendung.removeAttribute("hidden");
			begruendungButton.innerText = "Begründung ausblenden";
		}
		else
		{
			begruendung.setAttribute("hidden", "hidden");
			begruendungButton.innerText = "Begründung anzeigen";
		}
	}
	function changeAntworten()
	{
		let antwortA = document.getElementById("divA");
		let antwortB = document.getElementById("divB");
		let antwortC = document.getElementById("divC");
		let antwortD = document.getElementById("divD");
		let korrekteAntwort = document.getElementById("korrekteAntwort").value;
		let begruendungButton = document.getElementById("begruendungButton");
		begruendungButton.removeAttribute("disabled");
		begruendungButton.classList.remove("disabledButton");
		
		switch(korrekteAntwort)
		{
			case "A":
				antwortA.className = "richtigeAntwort";
				antwortB.className = "falscheAntwort";
				antwortC.className = "falscheAntwort";
				antwortD.className = "falscheAntwort";
				if(document.getElementById("antwortA").checked === true)
				{
					let anzahlRichtig = document.getElementById("anzahlRichtig").value;
					document.getElementById("anzahlRichtig").value = ++anzahlRichtig;
				}
				else
				{
					let anzahlFalsch = document.getElementById("anzahlFalsch").value;
					document.getElementById("anzahlFalsch").value = ++anzahlFalsch;
				}
				break;
			case "B":
				antwortA.className = "falscheAntwort";
				antwortB.className = "richtigeAntwort";
				antwortC.className = "falscheAntwort";
				antwortD.className = "falscheAntwort";
				if(document.getElementById("antwortB").checked === true)
				{
					let anzahlRichtig = document.getElementById("anzahlRichtig").value;
					document.getElementById("anzahlRichtig").value = ++anzahlRichtig;
				}
				else
				{
					let anzahlFalsch = document.getElementById("anzahlFalsch").value;
					document.getElementById("anzahlFalsch").value = ++anzahlFalsch;
				}
				break;
			case "C":
				antwortA.className = "falscheAntwort";
				antwortB.className = "falscheAntwort";
				antwortC.className = "richtigeAntwort";
				antwortD.className = "falscheAntwort";
				if(document.getElementById("antwortC").checked === true)
				{
					let anzahlRichtig = document.getElementById("anzahlRichtig").value;
					document.getElementById("anzahlRichtig").value = ++anzahlRichtig;
				}
				else
				{
					let anzahlFalsch = document.getElementById("anzahlFalsch").value;
					document.getElementById("anzahlFalsch").value = ++anzahlFalsch;
				}
				break;
			case "D":
				antwortA.className = "falscheAntwort";
				antwortB.className = "falscheAntwort";
				antwortC.className = "falscheAntwort";
				antwortD.className = "richtigeAntwort";
				if(document.getElementById("antwortD").checked === true)
				{
					let anzahlRichtig = document.getElementById("anzahlRichtig").value;
					document.getElementById("anzahlRichtig").value = ++anzahlRichtig;
				}
				else
				{
					let anzahlFalsch = document.getElementById("anzahlFalsch").value;
					document.getElementById("anzahlFalsch").value = ++anzahlFalsch;
				}
				break;
			default:
				break;
		}
		
		document.getElementById("antwortA").disabled = true;
		document.getElementById("antwortB").disabled = true;
		document.getElementById("antwortC").disabled = true;
		document.getElementById("antwortD").disabled = true;
		console.log(document.getElementById("anzahlRichtig").value);
		console.log(document.getElementById("anzahlFalsch").value);
	}
