//Dokument erstellt durch Fatih Doruk
package edu.thi.bean;
import java.io.Serializable;

public class ViewStudent_KarteikarteAbfrageBean implements Serializable
{
	private int karteikartenID;
	private int count;
	private int anzahlRichtig;
	private int anzahlFalsch;
	private String modulname;
	private String studiengangname;
	private String user;
	private String titel;
	private String frage;
	private String antwortA;
	private String antwortB;
	private String antwortC;
	private String antwortD;
	private char korrekteAntwort;
	private String begruendung;
	private byte[] bild;
	private boolean currentFlag;
	private boolean bildFlag;
	

	public int getKarteikartenID() {
		return karteikartenID;
	}
	public void setKarteikartenID(int karteikartenID) {
		this.karteikartenID = karteikartenID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getAnzahlRichtig() {
		return anzahlRichtig;
	}
	public void setAnzahlRichtig(int anzahlRichtig) {
		this.anzahlRichtig = anzahlRichtig;
	}
	public int getAnzahlFalsch() {
		return anzahlFalsch;
	}
	public void setAnzahlFalsch(int anzahlFalsch) {
		this.anzahlFalsch = anzahlFalsch;
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
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getFrage() {
		return frage;
	}
	public void setFrage(String frage) {
		this.frage = frage;
	}
	public String getAntwortA() {
		return antwortA;
	}
	public void setAntwortA(String antwortA) {
		this.antwortA = antwortA;
	}
	public String getAntwortB() {
		return antwortB;
	}
	public void setAntwortB(String antwortB) {
		this.antwortB = antwortB;
	}
	public String getAntwortC() {
		return antwortC;
	}
	public void setAntwortC(String antwortC) {
		this.antwortC = antwortC;
	}
	public String getAntwortD() {
		return antwortD;
	}
	public void setAntwortD(String antwortD) {
		this.antwortD = antwortD;
	}
	public char getKorrekteAntwort() {
		return korrekteAntwort;
	}
	public void setKorrekteAntwort(char korrekteAntwort) {
		this.korrekteAntwort = korrekteAntwort;
	}
	public String getBegruendung() {
		return begruendung;
	}
	public void setBegruendung(String begruendung) {
		this.begruendung = begruendung;
	}
	public byte[] getBild() {
		return bild;
	}
	public void setBild(byte[] bild) {
		this.bild = bild;
	}
	public boolean iscurrentFlag() {
		return currentFlag;
	}
	public void setcurrentFlag(boolean currentFlag) {
		this.currentFlag = currentFlag;
	}
	
	public boolean isBildFlag() {
		return bildFlag;
	}
	public void setBildFlag(boolean bildFlag) {
		this.bildFlag = bildFlag;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}