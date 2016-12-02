package team.wuxie.crowdfunding.util.fastjson;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import team.wuxie.crowdfunding.domain.ShippingStatus;
import team.wuxie.crowdfunding.domain.enums.PocketStatus;

/**
 * ClassName:PocketStatusSerializer <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月28日 上午10:56:59
 * @see 	 
 */
public class PocketStatusSerializer  implements ObjectSerializer, ObjectDeserializer{

	@Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            serializer.getWriter().writeInt(PocketStatus.UNUSED.getValue());
            return;
        }
        out.writeStringWithDoubleQuote(PocketStatus.name((PocketStatus) object), ' ');
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.lexer;
        int value = lexer.intValue();
        return (T) PocketStatus.of(value, PocketStatus.UNUSED);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

}

