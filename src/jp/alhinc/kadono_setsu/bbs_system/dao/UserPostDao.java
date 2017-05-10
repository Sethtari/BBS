package jp.alhinc.kadono_setsu.bbs_system.dao;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.UserPost;
import jp.alhinc.kadono_setsu.bbs_system.exception.SQLRuntimeException;

public class UserPostDao {

	public List<UserPost> getUserPost(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("posts.id,");
			sql.append("users.id AS user_id, ");
			sql.append("users.name,");
			sql.append("users.branch_id,");
			sql.append("users.position_id,");
			sql.append("posts.title,");
			sql.append("posts.text,");
			sql.append("posts.category,");
			sql.append("posts.created_at ");

			sql.append("FROM ");
			sql.append("posts ,");
			sql.append(" users ");

			sql.append("WHERE ");
			sql.append(" posts.users_id ");
			sql.append(" = ");
			sql.append(" users.id ");

			sql.append("ORDER BY ");
			sql.append("posts.created_at DESC");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toUserPostList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<UserPost> getCategories(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();


			sql.append("SELECT ");
			sql.append("posts.id,");
			sql.append("users.id AS user_id, ");
			sql.append("users.name,");
			sql.append("users.branch_id,");
			sql.append("users.position_id,");
			sql.append("posts.title,");
			sql.append("posts.text,");
			sql.append("posts.category,");
			sql.append("posts.created_at ");

			sql.append("FROM ");
			sql.append("posts ,");
			sql.append(" users ");

			sql.append("WHERE ");
			sql.append(" posts.users_id ");
			sql.append(" = ");
			sql.append(" users.id ");

			sql.append("GROUP BY ");
			sql.append("posts.category");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toUserPostList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<UserPost> categorize(Connection connection ,String category,String postMin,String postMax) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("posts.id, ");
			sql.append("users.id AS user_id, ");
			sql.append("users.name, ");
			sql.append("users.branch_id, ");
			sql.append("users.position_id, ");
			sql.append("posts.title, ");
			sql.append("posts.text, ");
			sql.append("posts.category, ");
			sql.append("posts.created_at ");

			sql.append("FROM ");
			sql.append("posts, users ");

			sql.append("WHERE ");
			sql.append("posts.users_id = users.id ");

			sql.append(" AND posts.created_at BETWEEN ");
			sql.append("? ");
			sql.append(" AND ");
			sql.append("? ");

			if(!category.isEmpty()){
				sql.append(" AND category = ? ");
			}

			sql.append("ORDER BY posts.created_at DESC");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, postMin);
			ps.setString(2, postMax);
			if(!category.isEmpty()){
				ps.setString(3, category);
			}
			ResultSet rs = ps.executeQuery();

			List<UserPost> ret = toUserPostList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPost> toUserPostList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {


				int postsId = rs.getInt("id");
				int usersId = rs.getInt("user_id");
				String usersName = rs.getString("name");
				String usersBranchId = rs.getString("branch_id");
				String usersPositionId = rs.getString("position_id");
				String postsTitle = rs.getString("title");
				String postsText = rs.getString("text");
				String postsCategory = rs.getString("category");
				Timestamp postsCreatedAt = rs.getTimestamp("created_at");

				UserPost post = new UserPost();
				post.setPostsId(postsId);
				post.setUsersId(usersId);
				post.setUsersName(usersName);
				post.setUsersBranchId(usersBranchId);
				post.setUsersPositionId(usersPositionId);
				post.setPostsTitle(postsTitle);
				post.setPostsText(postsText);
				post.setPostsCategory(postsCategory);
				post.setPostsCreatedAt(postsCreatedAt);
				ret.add(post);


			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
