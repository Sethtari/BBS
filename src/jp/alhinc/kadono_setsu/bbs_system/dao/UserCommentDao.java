package jp.alhinc.kadono_setsu.bbs_system.dao;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.UserComment;
import jp.alhinc.kadono_setsu.bbs_system.exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getUserComment(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("comments.id, ");
			sql.append("comments.post_id, ");
			sql.append("users.id AS user_id, ");
			sql.append("users.name, ");
			sql.append("users.branch_id, ");
			sql.append("users.position_id, ");
			sql.append("comments.text, ");
			sql.append("comments.created_at ");
			sql.append("FROM ");
			sql.append("comments ,");
			sql.append(" users ");
			sql.append("WHERE ");
			sql.append("comments.user_id ");
			sql.append("= users.id ");
			sql.append("ORDER BY ");
			sql.append("comments.post_id ASC, ");
			sql.append("comments.created_at ASC");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {

				int commentsId = rs.getInt("id");
				int postsId = rs.getInt("post_id");
				String usersName = rs.getString("name");
				int userId = rs.getInt("user_id");
				String usersBranchId = rs.getString("branch_id");
				String usersPositionId = rs.getString("position_id");
				String commentsText = rs.getString("text");
				Timestamp commentsCreatedAt = rs.getTimestamp("created_at");

				UserComment comment = new UserComment();
				comment.setCommentsId(commentsId);
				comment.setPostsId(postsId);
				comment.setUsersId(userId);
				comment.setUsersName(usersName);
				comment.setUsersBranchId(usersBranchId);
				comment.setUsersPositionId(usersPositionId);
				comment.setCommentsText(commentsText);
				comment.setCommentsCreatedAt(commentsCreatedAt);
				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
