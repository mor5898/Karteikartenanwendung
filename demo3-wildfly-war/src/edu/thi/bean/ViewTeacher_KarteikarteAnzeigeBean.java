package edu.thi.bean;
import java.io.Serializable;

public class ViewTeacher_KarteikarteAnzeigeBean implements Serializable
{
	private String studiengang;
	private String modul;
	private int karteikarteID;
	private String titel;
	
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
