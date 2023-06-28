package edu.thi.bean;
import java.io.Serializable;
//Erstellt durch Riza Dursun und Fatih Doruk
public class ViewTeacher_KarteikarteAnzeigeBean implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String studiengang;
	private String modul;
	private int karteikarteID;
	private String titel;
	private String userId;
	private String fragentext;
	
	public String getFragentext() {
		return fragentext;
	}
	public void setFragentext(String fragentext) {
		this.fragentext = fragentext;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudiengang() {
		return studiengang;
	}
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}
	public String getModul() {
		return modul;
	}
	public void setModul(String modul) {
		this.modul = modul;
	}
	public int getKarteikarteID() {
		return karteikarteID;
	}
	public void setKarteikarteID(int karteikarteID) {
		this.karteikarteID = karteikarteID;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
}
