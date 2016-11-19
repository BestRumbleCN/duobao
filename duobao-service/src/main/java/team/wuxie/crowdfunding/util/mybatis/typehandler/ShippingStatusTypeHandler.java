package team.wuxie.crowdfunding.util.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import team.wuxie.crowdfunding.domain.ShippingStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ShippingRecordStatusTypeHandler <br/>
 */
public class ShippingStatusTypeHandler implements TypeHandler<ShippingStatus> {

	@Override
	public void setParameter(PreparedStatement ps, int i, ShippingStatus bidStatus, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, bidStatus.getValue());
	}

	@Override
	public ShippingStatus getResult(ResultSet rs, String columnName) throws SQLException {
		return ShippingStatus.of(Integer.valueOf(rs.getString(columnName)), ShippingStatus.TO_SHIP);
	}

	@Override
	public ShippingStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
		return ShippingStatus.of(rs.getInt(columnIndex), ShippingStatus.TO_SHIP);
	}

	@Override
	public ShippingStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return ShippingStatus.of(cs.getInt(columnIndex), ShippingStatus.TO_SHIP);
	}

}
