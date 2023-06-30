//Erstellt durch Moritz Reindl
package edu.thi.bean;

import java.io.Serializable;

public class ViewTeacher_StudiengaengeBean implements Serializable {

	private static final long serialVersionUID = 1L;
    private String studiengangname;
    private String userId;

    public String getUserId() {
		return userId;
	}

	public String getStudiengangname() {
		return studiengangname;
	}

	public void setStudiengangname(String studiengangname) {
		this.studiengangname = studiengangname;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

