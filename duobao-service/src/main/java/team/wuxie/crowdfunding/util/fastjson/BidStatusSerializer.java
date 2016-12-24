package team.wuxie.crowdfunding.util.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import team.wuxie.crowdfunding.domain.enums.BidStatus;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * <p>
 * 自定义枚举序列化方法
 * </p>
 *
 * @author wushige
 * @date 2016-06-08 16:56
 */
public class BidStatusSerializer implements ObjectSerializer, ObjectDeserializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            serializer.getWriter().writeInt(BidStatus.WAIT.getValue());
            return;
        }
        out.writeStringWithDoubleQuote(BidStatus.name((BidStatus) object), ' ');
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.lexer;
        int value = lexer.intValue();
        return (T) BidStatus.of(value, BidStatus.WAIT);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
