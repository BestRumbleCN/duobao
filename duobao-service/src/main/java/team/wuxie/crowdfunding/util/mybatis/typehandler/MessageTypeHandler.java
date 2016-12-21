package team.wuxie.crowdfunding.util.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import team.wuxie.crowdfunding.domain.enums.MessageType;

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
public class MessageTypeHandler implements TypeHandler<MessageType> {

    public MessageTypeHandler() {
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, MessageType messageType, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, messageType.getValue());
    }

    @Override
    public MessageType getResult(ResultSet rs, String columnName) throws SQLException {
        return MessageType.of(Integer.valueOf(rs.getString(columnName)), MessageType.DEFAULT);
    }

    @Override
    public MessageType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return MessageType.of(rs.getInt(columnIndex), MessageType.DEFAULT);
    }

    @Override
    public MessageType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return MessageType.of(cs.getInt(columnIndex), MessageType.DEFAULT);
    }
}
