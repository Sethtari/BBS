package jp.alhinc.kadono_setsu.bbs_system.beans;

import java.io.Serializable;
import java.util.Date;

public class UserPost implements Serializable {
	private static final long serialVersionUID = 1L;

	private int postsId;
	private String usersName;
	private String usersBranchId;
	private String usersPositionId;
	private String postsTitle;
	private String postsText;
	private String postsCategory;
	private Date postsCreatedAt;


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

	public String getPostsTitle() {
		return postsTitle;
	}

	public void setPostsTitle(String postsTitle) {
		this.postsTitle = postsTitle;
	}


	public String getPostsText() {
		return postsText;
	}

	public void setPostsText(String postsText) {
		this.postsText = postsText;
	}

	public String getPostsCategory() {
		return postsCategory;
	}

	public void setPostsCategory(String postsCategory) {
		this.postsCategory = postsCategory;
	}


	public Date getPostsCreatedAt() {
		return postsCreatedAt;
	}

	public void setPostsCreatedAt(Date postsCreatedAt) {
		this.postsCreatedAt = postsCreatedAt;
	}

}
