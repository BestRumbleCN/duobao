package team.wuxie.crowdfunding.util.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import team.wuxie.crowdfunding.domain.enums.BannerType;

/**
 * ClassName:TradeTypeHandler <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月14日 下午7:25:41
 * @see
 */
public class BannerTypeHandler implements TypeHandler<BannerType> {

	@Override
	public void setParameter(PreparedStatement ps, int i, BannerType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public BannerType getResult(ResultSet rs, String columnName) throws SQLException {
		return BannerType.of(Integer.valueOf(rs.getString(columnName)), BannerType.GOODS);
	}

	@Override
	public BannerType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return BannerType.of(rs.getInt(columnIndex), BannerType.GOODS);
	}

	@Override
	public BannerType getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return BannerType.of(cs.getInt(columnIndex), BannerType.GOODS);
	}

}
