package edu.thi.bean;
//Erstellt durch Riza Dursun und Fatih Doruk
import java.io.Serializable;

public class ViewTeacher_KarteikarteErstellenBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String antwortA;
	private String antwortB;
	private String antwortC;
	private String antwortD;
	private String fragentext;
	private String korrekteAntwort;
	private String begruendung;
	private String titel;
	private Integer karteikartenId;
	private String modulname;
	private String studiengangname;
	private String userId;
	// Hier muss noch dieses Attribut ergänzt werden
	private byte[] bild;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public byte[] getBild() {
		return bild;
	}

	public void setBild(byte[] bild) {
		this.bild = bild;
	}

	public String getModulname() {
		return modulname;
	}

	public void setModulname(String modulname) {
		this.modulname = modulname;
	}

	public String getStudiengangname() {
		return studiengangname;
	}

	public void setStudiengangname(String studiengangname) {
		this.studiengangname = studiengangname;
	}
	/*
	 * public byte[] getBild() { return bild; } public void setBild(byte[] bild) {
	 * this.bild = bild; }
	 */
	public String getantwortA() {
		return antwortA;
	}

	public void setantwortA(String antwortA) {
		this.antwortA = antwortA;
	}

	public String getantwortB() {
		return antwortB;
	}

	public void setantwortB(String antwortB) {
		this.antwortB = antwortB;
	}

	public String getantwortC() {
		return antwortC;
	}

	public void setantwortC(String antwortC) {
		this.antwortC = antwortC;
	}

	public String getantwortD() {
		return antwortD;
	}

	public void setantwortD(String antwortD) {
		this.antwortD = antwortD;
	}

	public String getfragentext() {
		return fragentext;
	}

	public void setfragentext(String fragentext) {
		this.fragentext = fragentext;
	}

	public String getbegruendung() {
		return begruendung;
	}

	public void setbegruendung(String begruendung) {
		this.begruendung = begruendung;
	}

	public String getkorrekteAntwort() {
		return korrekteAntwort;
	}

	public void setkorrekteAntwort(String korrekteAntwort) {
		this.korrekteAntwort = korrekteAntwort;
	}

	public String gettitel() {
		return titel;
	}

	public void settitel(String titel) {
		this.titel = titel;
	}

	public Integer getkarteikartenId() {
		return karteikartenId;
	}

	public void setkarteikartenId(Integer karteikartenId) {
		this.karteikartenId = karteikartenId;
	}

}
