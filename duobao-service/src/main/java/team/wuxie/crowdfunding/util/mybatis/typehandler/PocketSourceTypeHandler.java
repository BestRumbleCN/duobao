package team.wuxie.crowdfunding.util.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import team.wuxie.crowdfunding.domain.enums.PocketSource;

/**
 * ClassName:PocketSourceTypeHanlder <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月14日 下午7:28:14
 * @see
 */
public class PocketSourceTypeHandler implements TypeHandler<PocketSource> {

	@Override
	public void setParameter(PreparedStatement ps, int i, PocketSource parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public PocketSource getResult(ResultSet rs, String columnName) throws SQLException {
		return PocketSource.of(Integer.valueOf(rs.getString(columnName)), PocketSource.BACKEND);
	}

	@Override
	public PocketSource getResult(ResultSet rs, int columnIndex) throws SQLException {
		return PocketSource.of(rs.getInt(columnIndex), PocketSource.BACKEND);
	}

	@Override
	public PocketSource getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return PocketSource.of(cs.getInt(columnIndex), PocketSource.BACKEND);
	}

}
