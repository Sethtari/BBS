package jp.alhinc.kadono_setsu.bbs_system.beans;


import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String password;
	private String name;
	private String branchId;
	private String positionId;
	private Integer isStopped;
	private Integer passCheck;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginID(String loginId) {
		this.loginId = loginId;
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

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public Integer getIsStopped() {
		return isStopped;
	}

	public void setIsStopped(int isStopped) {
		this.isStopped = isStopped;
	}

	public Integer getPassCheck() {
		return passCheck;
	}

	public void setPassCheck(int passCheck) {
		this.passCheck = passCheck;
	}
}
