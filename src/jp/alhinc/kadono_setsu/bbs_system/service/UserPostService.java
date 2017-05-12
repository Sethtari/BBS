package jp.alhinc.kadono_setsu.bbs_system.service;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;
import static jp.alhinc.kadono_setsu.bbs_system.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.UserPost;
import jp.alhinc.kadono_setsu.bbs_system.dao.UserPostDao;

public class UserPostService {

	public List<UserPost> getPostsList() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostDao postDao = new UserPostDao();
			List<UserPost> ret = postDao.getUserPost(connection);

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

	public List<UserPost> getCategories() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostDao postDao = new UserPostDao();
			List<UserPost> ret = postDao.getCategories(connection);

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

	public List<UserPost> getCategorizedList(String category,String postMin,String postMax) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostDao postDao = new UserPostDao();

			List<UserPost> ret = postDao.categorize(connection, category,postMin,postMax);

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

	public UserPost getWhenCreated() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostDao postDao = new UserPostDao();
			UserPost ret = postDao.getWhenCreated(connection);

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
}
