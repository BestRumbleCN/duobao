package team.wuxie.crowdfunding.util.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import team.wuxie.crowdfunding.domain.BidStatus;

/**
 * ClassName:BidStatusTypeHandler <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月24日 下午2:35:49
 * @see
 */
public class BidStatusTypeHandler implements TypeHandler<BidStatus> {

	@Override
	public void setParameter(PreparedStatement ps, int i, BidStatus bidStatus, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, bidStatus.getValue());
	}

	@Override
	public BidStatus getResult(ResultSet rs, String columnName) throws SQLException {
		return BidStatus.of(Integer.valueOf(rs.getString(columnName)), BidStatus.WAIT);
	}

	@Override
	public BidStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
		return BidStatus.of(rs.getInt(columnIndex), BidStatus.WAIT);
	}

	@Override
	public BidStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return BidStatus.of(cs.getInt(columnIndex), BidStatus.WAIT);
	}

}
