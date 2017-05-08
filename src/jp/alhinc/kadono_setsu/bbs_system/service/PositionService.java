package jp.alhinc.kadono_setsu.bbs_system.service;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;
import static jp.alhinc.kadono_setsu.bbs_system.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.Position;
import jp.alhinc.kadono_setsu.bbs_system.dao.PositionDao;

public class PositionService {

	public List<Position> getPositionList() {

		Connection connection = null;
		try {
			connection = getConnection();

			PositionDao listDao = new PositionDao();
			List<Position> ret = listDao.getPositionList(connection);

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