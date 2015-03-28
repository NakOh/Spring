package springbook.user.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {
	@Override
	public PreparedStatement makePreparedStatement(java.sql.Connection c)
			throws SQLException {
		PreparedStatement ps = c.prepareStatement("delete from users");
		return ps;
	}
}
