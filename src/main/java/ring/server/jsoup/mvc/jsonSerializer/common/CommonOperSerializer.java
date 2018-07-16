package ring.server.jsoup.mvc.jsonSerializer.common;

import java.io.IOException;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 通用序列化
 * @author ring
 * @date 2017年12月22日 下午4:08:48
 * @version V1.0
 */
public class CommonOperSerializer extends JsonSerializer<String>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void serialize(String value, JsonGenerator jg, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		try {
			//序列对象
			Object object = jg.getCurrentValue();
			
			Field field = object.getClass().getDeclaredField("id");
			field.setAccessible(true);
			Object idValue = field.get(object);
			
			StringBuffer sb = new StringBuffer();
			sb.append("<a style=\"text-decoration:none;font-size: 18px;\" target=\"_blank\" data-type=\"edit\" data-href=\"edit/"+idValue+"\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe60c;</i></a>");
			sb.append("<a style=\"text-decoration:none;font-size: 18px;margin-left:5px\" data-type=\"delete\" target=\"_blank\" data-href=\"delete/"+idValue+"\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>");
			jg.writeString(sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
