package ring.server.jsoup.mvc.jsonSerializer.page;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import ring.server.jsoup.mvc.model.page.PageList;

/**
 * 通用序列化
 * @author ring
 * @date 2017年12月22日 下午4:08:48
 * @version V1.0
 */
public class PageListOperSerializer extends JsonSerializer<String>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void serialize(String value, JsonGenerator jg, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		try {
			//序列对象
			PageList pageList = (PageList) jg.getCurrentValue();
			StringBuffer sb = new StringBuffer();
			sb.append("<a style=\"text-decoration:none;font-size: 18px;\" target=\"_blank\"  href=\"/detail/imgs/"+pageList.getSource()+"/"+pageList.getId()+"\" title=\"图片\"><i class=\"Hui-iconfont\">&#xe613;</i></a>");
			jg.writeString(sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
