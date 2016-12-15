package team.wuxie.crowdfunding.util.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import team.wuxie.crowdfunding.domain.enums.TradeType;

/**
 * ClassName:TradeTypeHandler <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月14日 下午7:25:41
 * @see
 */
public class TradeTypeHandler implements TypeHandler<TradeType> {

	@Override
	public void setParameter(PreparedStatement ps, int i, TradeType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public TradeType getResult(ResultSet rs, String columnName) throws SQLException {
		return TradeType.of(Integer.valueOf(rs.getString(columnName)), TradeType.GOODS);
	}

	@Override
	public TradeType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return TradeType.of(rs.getInt(columnIndex), TradeType.GOODS);
	}

	@Override
	public TradeType getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return TradeType.of(cs.getInt(columnIndex), TradeType.GOODS);
	}

}
