package jp.alhinc.kadono_setsu.bbs_system.dao;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.alhinc.kadono_setsu.bbs_system.beans.Post;
import jp.alhinc.kadono_setsu.bbs_system.exception.SQLRuntimeException;

public class PostDao {

	public void insert(Connection connection, Post post) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts ( ");
			sql.append("users_id, ");
			sql.append("title, ");
			sql.append("text, ");
			sql.append("category, ");
			sql.append("created_at");
			sql.append(") VALUES (");
			sql.append("? ,"); // users_id
			sql.append("? ,"); // title
			sql.append("? ,"); // text
			sql.append("? ,"); // category
			sql.append("CURRENT_TIMESTAMP"); // created_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, post.getUsersId());
			ps.setString(2, post.getTitle());
			ps.setString(3, post.getText());
			ps.setString(4,  post.getCategory());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public void delete(Connection connection, Post post) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM posts WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, post.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
