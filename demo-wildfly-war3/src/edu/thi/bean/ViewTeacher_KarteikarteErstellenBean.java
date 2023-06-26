package edu.thi.bean;

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
	private Integer karteikartenId = 1;
	private Integer modulId;
	private Integer studienfachId;
	private Integer userId;
	// Hier muss noch dieses Attribut ergänzt werden
	private byte[] bild;

	
	public byte[] getBild() 
	{ 
		return bild; 
	}
	 
	public void setBild(byte[] bild)
	{
		this.bild = bild; 
	}
	
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

	public Integer getmodulId() {
		return modulId;
	}

	public void setmodulId(Integer modulId) {
		this.modulId = modulId;
	}

	public Integer getuserId() {
		return userId;
	}

	public void setuserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getstudienfachId() {
		return studienfachId;
	}

	public void setstudienfachId(Integer studienfachId) {
		this.studienfachId = studienfachId;
	}
}
