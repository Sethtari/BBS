package jp.alhinc.kadono_setsu.bbs_system.dao;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.Branch;
import jp.alhinc.kadono_setsu.bbs_system.exception.SQLRuntimeException;

public class BranchDao {

	private List<Branch> toBranchList(ResultSet rs) throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");

				Branch branch = new Branch();
				branch.setId(id);
				branch.setName(name);

				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Branch> getBranchList(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT * FROM branches");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranchList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
