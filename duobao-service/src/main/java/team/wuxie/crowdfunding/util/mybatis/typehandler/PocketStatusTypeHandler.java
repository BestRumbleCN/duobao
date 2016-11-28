package team.wuxie.crowdfunding.util.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import team.wuxie.crowdfunding.domain.enums.PocketStatus;

/**
 * ClassName:PocketStatusTypeHanlder <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月14日 下午7:28:14
 * @see 	 
 */
public class PocketStatusTypeHandler implements TypeHandler<PocketStatus> {

	@Override
	public void setParameter(PreparedStatement ps, int i, PocketStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public PocketStatus getResult(ResultSet rs, String columnName) throws SQLException {
		return PocketStatus.of(Integer.valueOf(rs.getString(columnName)), PocketStatus.UNUSED);
		}

	@Override
	public PocketStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
		return PocketStatus.of(rs.getInt(columnIndex), PocketStatus.UNUSED);
		}

	@Override
	public PocketStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return PocketStatus.of(cs.getInt(columnIndex), PocketStatus.UNUSED);
		}

}

