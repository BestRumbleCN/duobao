package team.wuxie.crowdfunding.util.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import team.wuxie.crowdfunding.domain.enums.TradeStatus;

/**
 * ClassName:TradeStatusTypeHanlder <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月14日 下午7:28:14
 * @see 	 
 */
public class TradeStatusTypeHandler implements TypeHandler<TradeStatus> {

	@Override
	public void setParameter(PreparedStatement ps, int i, TradeStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public TradeStatus getResult(ResultSet rs, String columnName) throws SQLException {
		return TradeStatus.of(Integer.valueOf(rs.getString(columnName)), TradeStatus.CANCLE);
		}

	@Override
	public TradeStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
		return TradeStatus.of(rs.getInt(columnIndex), TradeStatus.CANCLE);
		}

	@Override
	public TradeStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return TradeStatus.of(cs.getInt(columnIndex), TradeStatus.CANCLE);
		}

}

