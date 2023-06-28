package edu.thi.bean;

import java.io.Serializable;

//Erstellt durch Moritz Reindl
public class ViewTeacher_ModuleBean implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private String modulname;
	private String studiengangname;
    private String userId;
    
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ViewTeacher_ModuleBean [modulname=" + modulname + ", studiengangname=" + studiengangname + ", userId="
				+ userId + "]";
	}
    
}
