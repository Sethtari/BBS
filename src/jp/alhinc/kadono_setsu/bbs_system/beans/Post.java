package jp.alhinc.kadono_setsu.bbs_system.beans;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int users_id;
	private String title;
	private String text;
	private String category;
	private Date created_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsersId() {
		return users_id;
	}

	public void setUsersId(int users_Id) {
		this.users_id = users_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCretatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

}
