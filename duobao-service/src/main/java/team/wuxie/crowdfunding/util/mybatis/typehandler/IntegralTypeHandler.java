package team.wuxie.crowdfunding.util.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import team.wuxie.crowdfunding.domain.enums.IntegralType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * mybatis自定义枚举TypeHandler
 * </p>
 *
 * @author wushige
 * @date 2016-06-08 18:13
 */
public class IntegralTypeHandler implements TypeHandler<IntegralType> {

    public IntegralTypeHandler() {
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, IntegralType integralType, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, integralType.getValue());
    }

    @Override
    public IntegralType getResult(ResultSet rs, String columnName) throws SQLException {
        return IntegralType.of(Integer.valueOf(rs.getString(columnName)), IntegralType.DEFAULT);
    }

    @Override
    public IntegralType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return IntegralType.of(rs.getInt(columnIndex), IntegralType.DEFAULT);
    }

    @Override
    public IntegralType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return IntegralType.of(cs.getInt(columnIndex), IntegralType.DEFAULT);
    }
}
