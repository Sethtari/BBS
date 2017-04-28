package jp.alhinc.kadono_setsu.bbs_system.beans;


import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login_id;
	private String password;
	private String name;
	private String branch_id;
	private String position_id;
	private Integer is_stopped;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getLoginID() {
		return login_id;
	}

	public void setLoginID(String login_ID) {
		this.login_id = login_ID;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchID() {
		return branch_id;
	}

	public void setBranchID(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getPositionID() {
		return position_id;
	}

	public void setPositionID(String position_id) {
		this.position_id = position_id;
	}

	public Integer getIsStopped() {
		return is_stopped;
	}

	public void setIsStopped(int is_Stopped) {
		this.is_stopped = is_Stopped;
	}
}
