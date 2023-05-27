package edu.thi.bean;
//Erstellt durch Moritz Reindl
import java.io.Serializable;

public class ViewTeacher_StudiengaengeBean implements Serializable {
    private int studiengangId;
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

	public int getStudiengangId() {
        return studiengangId;
    }

    public void setStudiengangId(int studienfachId) {
        this.studiengangId = studienfachId;
    }
}

