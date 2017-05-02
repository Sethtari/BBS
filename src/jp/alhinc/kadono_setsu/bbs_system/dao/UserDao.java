package jp.alhinc.kadono_setsu.bbs_system.dao;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.exception.NoRowsUpdatedRuntimeException;
import jp.alhinc.kadono_setsu.bbs_system.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String login_id,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 == userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branch_id = rs.getString("branch_id");
				String position_id = rs.getString("position_id");
				Integer is_stopped = rs.getInt("is_stopped");

				User user = new User();
				user.setId(id);
				user.setLoginID(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branch_id);
				user.setPositionId(position_id);
				user.setIsStopped(is_stopped);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id ,");
			sql.append("password ,");
			sql.append("name ,");
			sql.append("branch_id ,");
			sql.append("position_id ,");
			sql.append("is_stopped");
			sql.append(") VALUES (");
			sql.append("? ,"); // login__id
			sql.append("? ,"); // password
			sql.append("? ,"); // name
			sql.append("? ,"); // branch__id
			sql.append("? ,"); // position__id
			sql.append("0"); // is_stopped
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBranchId());
			ps.setString(5, user.getPositionId());



			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET ");
			sql.append("login_id = ? ,");
			sql.append("password = ? ,");
			sql.append("name = ? ,");
			sql.append("branch_id = ? ,");
			sql.append("position_id = ? ,");
			sql.append("is_stopped = ?");
			sql.append(" WHERE");
			sql.append(" id = ? ;");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBranchId());
			ps.setString(5, user.getPositionId());
			ps.setInt(6, user.getIsStopped());
			ps.setInt(7, user.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<User> getUserList(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");
			sql.append("ORDER BY id ASC");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void isStoppedUpdate(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET is_stopped = ? WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, user.getIsStopped());
			ps.setInt(2, user.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void deleteUser(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM users WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, user.getId());
System.out.println(user.getId());
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}