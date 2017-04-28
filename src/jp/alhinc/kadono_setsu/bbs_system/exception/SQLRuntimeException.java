package jp.alhinc.kadono_setsu.bbs_system.exception;

import java.sql.SQLException;

public class SQLRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SQLRuntimeException(SQLException cause) {
		super(cause);
	}

}
