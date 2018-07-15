package ring.server.jsoup.mvc.jsonSerializer.index;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import ring.server.jsoup.mvc.model.page.PageIndex;

/**
 * 通用序列化
 * @author ring
 * @date 2017年12月22日 下午4:08:48
 * @version V1.0
 */
public class PageIndexOperSerializer extends JsonSerializer<String>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void serialize(String value, JsonGenerator jg, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		try {
			//序列对象
			PageIndex pageIndex = (PageIndex) jg.getCurrentValue();
			StringBuffer sb = new StringBuffer();
			sb.append("<a style=\"text-decoration:none;font-size: 18px;\" target=\"_blank\" data-type=\"edit\" data-href=\"edit/"+pageIndex.getEnName()+"\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe60c;</i></a>");
			sb.append("<a style=\"text-decoration:none;font-size: 18px;margin-left:5px\" data-type=\"delete\" target=\"_blank\" data-href=\"delete/"+pageIndex.getEnName()+"\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>");
			jg.writeString(sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
