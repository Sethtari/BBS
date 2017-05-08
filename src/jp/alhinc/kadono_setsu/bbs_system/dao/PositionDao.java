package jp.alhinc.kadono_setsu.bbs_system.dao;

import static jp.alhinc.kadono_setsu.bbs_system.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.alhinc.kadono_setsu.bbs_system.beans.Position;
import jp.alhinc.kadono_setsu.bbs_system.exception.SQLRuntimeException;

public class PositionDao {

	private List<Position> toPositionList(ResultSet rs) throws SQLException {

		List<Position> ret = new ArrayList<Position>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");

				Position position = new Position();
				position.setId(id);
				position.setName(name);

				ret.add(position);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Position> getPositionList(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT * FROM positions");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Position> ret = toPositionList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
