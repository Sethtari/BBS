package jp.alhinc.kadono_setsu.bbs_system.service;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;
import static jp.alhinc.kadono_setsu.bbs_system.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.dao.UserDao;
import jp.alhinc.kadono_setsu.bbs_system.utils.CipherUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();
			if((user.getPassword()) != null){
			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);
			}

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			if(user.getPassCheck() == 0){
			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);
			}

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public User getUser(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, userId);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


	public List<User> getUserList() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao listDao = new UserDao();
			List<User> ret = listDao.getUserList(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void changeStoppedOrNot(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.isStoppedUpdate(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void deleteUser(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();

			userDao.deleteUser(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}