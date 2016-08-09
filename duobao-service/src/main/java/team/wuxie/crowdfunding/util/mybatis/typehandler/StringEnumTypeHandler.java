package team.wuxie.crowdfunding.util.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import team.wuxie.crowdfunding.domain.Role;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * 自定义枚举Handler
 * </p>
 *
 * @author wushige
 * @date 2016-08-09 13:56
 */
public class StringEnumTypeHandler implements TypeHandler<Role> {

    public StringEnumTypeHandler() {
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, Role role, JdbcType jdbcType) throws SQLException {
        ps.setString(i, role.name());
    }

    @Override
    public Role getResult(ResultSet rs, String columnName) throws SQLException {
        return Role.of(rs.getString(columnName));
    }

    @Override
    public Role getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Role.of(String.valueOf(columnIndex));
    }

    @Override
    public Role getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Role.of(String.valueOf(columnIndex));
    }
}
