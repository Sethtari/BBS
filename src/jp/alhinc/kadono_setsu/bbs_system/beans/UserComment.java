package jp.alhinc.kadono_setsu.bbs_system.beans;

import java.io.Serializable;
import java.util.Date;

public class UserComment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int commentsId;
	private int usersId;
	private int postsId;
	private String usersName;
	private String usersBranchId;
	private String usersPositionId;
	private String commentsText;
	private Date commentsCreatedAt;


	public int getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(int commentsId) {
		this.commentsId = commentsId;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public int getPostsId() {
		return postsId;
	}

	public void setPostsId(int postsId) {
		this.postsId = postsId;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getUsersBranchId() {
		return usersBranchId;
	}

	public void setUsersBranchId(String usersBranchId) {
		this.usersBranchId = usersBranchId;
	}

	public String getUsersPositionId() {
		return usersPositionId;
	}

	public void setUsersPositionId(String usersPositionId) {
		this.usersPositionId = usersPositionId;
	}


	public String getCommentsText() {
		return commentsText;
	}

	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}


	public Date getCommentsCreatedAt() {
		return commentsCreatedAt;
	}

	public void setCommentsCreatedAt(Date commentsCreatedAt) {
		this.commentsCreatedAt = commentsCreatedAt;
	}

}
