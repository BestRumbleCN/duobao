package team.wuxie.crowdfunding.util.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import team.wuxie.crowdfunding.domain.enums.CodeType;

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
public class CodeTypeHandler implements TypeHandler<CodeType> {

    public CodeTypeHandler() {
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, CodeType codeType, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, codeType.getValue());
    }

    @Override
    public CodeType getResult(ResultSet rs, String columnName) throws SQLException {
        return CodeType.of(Integer.valueOf(rs.getString(columnName)), CodeType.DEFAULT);
    }

    @Override
    public CodeType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return CodeType.of(rs.getInt(columnIndex), CodeType.DEFAULT);
    }

    @Override
    public CodeType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return CodeType.of(cs.getInt(columnIndex), CodeType.DEFAULT);
    }
}
