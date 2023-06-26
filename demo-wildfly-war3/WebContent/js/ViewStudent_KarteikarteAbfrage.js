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
		
		switch(korrekteAntwort)
		{
			case "a":
				antwortA.className = "richtigeAntwort";
				antwortB.className = "falscheAntwort";
				antwortC.className = "falscheAntwort";
				antwortD.className = "falscheAntwort";
				break;
			case "b":
				antwortA.className = "falscheAntwort";
				antwortB.className = "richtigeAntwort";
				antwortC.className = "falscheAntwort";
				antwortD.className = "falscheAntwort";
				break;
			case "c":
				antwortA.className = "falscheAntwort";
				antwortB.className = "falscheAntwort";
				antwortC.className = "richtigeAntwort";
				antwortD.className = "falscheAntwort";
				break;
			case "d":
				antwortA.className = "falscheAntwort";
				antwortB.className = "falscheAntwort";
				antwortC.className = "falscheAntwort";
				antwortD.className = "richtigeAntwort";
				break;
			default:
				break;
		}
		
		document.getElementById("antwortA").disabled = true;
		document.getElementById("antwortB").disabled = true;
		document.getElementById("antwortC").disabled = true;
		document.getElementById("antwortD").disabled = true;
	}
