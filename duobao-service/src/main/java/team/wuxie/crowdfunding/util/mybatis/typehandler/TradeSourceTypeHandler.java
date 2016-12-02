package team.wuxie.crowdfunding.util.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import team.wuxie.crowdfunding.domain.enums.TradeSource;

/**
 * ClassName:TradeSourceTypehandler <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月14日 下午6:51:06
 * @see 	 
 */
public class TradeSourceTypeHandler implements TypeHandler<TradeSource> {

	@Override
	public void setParameter(PreparedStatement ps, int i, TradeSource parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public TradeSource getResult(ResultSet rs, String columnName) throws SQLException {
		return TradeSource.of(Integer.valueOf(rs.getString(columnName)), TradeSource.OTHERS);
	}

	@Override
	public TradeSource getResult(ResultSet rs, int columnIndex) throws SQLException {
		return TradeSource.of(Integer.valueOf(rs.getInt(columnIndex)), TradeSource.OTHERS);
	}

	@Override
	public TradeSource getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return TradeSource.of(cs.getInt(columnIndex), TradeSource.OTHERS);
	}

}

