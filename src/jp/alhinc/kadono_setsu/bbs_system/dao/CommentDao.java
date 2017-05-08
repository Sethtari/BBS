package jp.alhinc.kadono_setsu.bbs_system.dao;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.alhinc.kadono_setsu.bbs_system.beans.Comment;
import jp.alhinc.kadono_setsu.bbs_system.exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("post_id, ");
			sql.append("user_id, ");
			sql.append("text, ");
			sql.append("created_at");
			sql.append(") VALUES (");
			sql.append("? ,"); // post_id
			sql.append("? ,"); // user_id
			sql.append("? ,"); // text
			sql.append("CURRENT_TIMESTAMP"); // created_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getPostId());
			ps.setInt(2, comment.getUserId());
			ps.setString(3, comment.getText());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void delete(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
