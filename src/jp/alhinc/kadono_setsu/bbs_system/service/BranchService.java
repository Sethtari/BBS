package jp.alhinc.kadono_setsu.bbs_system.service;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;
import static jp.alhinc.kadono_setsu.bbs_system.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.Branch;
import jp.alhinc.kadono_setsu.bbs_system.dao.BranchDao;

public class BranchService {

	public List<Branch> getBranchList() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao listDao = new BranchDao();
			List<Branch> ret = listDao.getBranchList(connection);

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