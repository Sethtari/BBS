package jp.alhinc.kadono_setsu.bbs_system.service;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;
import static jp.alhinc.kadono_setsu.bbs_system.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.Message;
import jp.alhinc.kadono_setsu.bbs_system.beans.UserMessage;
import jp.alhinc.kadono_setsu.bbs_system.dao.MessageDao;
import jp.alhinc.kadono_setsu.bbs_system.dao.UserMessageDao;

public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, message);

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

	private static final int LIMIT_NUM = 1000;

	public List<UserMessage> getMessage() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getUserMessages(connection, LIMIT_NUM);

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
