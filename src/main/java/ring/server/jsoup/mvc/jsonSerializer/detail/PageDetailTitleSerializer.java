package ring.server.jsoup.mvc.jsonSerializer.detail;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import ring.server.jsoup.mvc.model.page.PageDetail;

/**
 * 通用序列化
 * @author ring
 * @date 2017年12月22日 下午4:08:48
 * @version V1.0
 */
public class PageDetailTitleSerializer extends JsonSerializer<String>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void serialize(String value, JsonGenerator jg, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		try {
			//序列对象
			PageDetail pageDetail = (PageDetail) jg.getCurrentValue();
			StringBuffer sb = new StringBuffer();
			sb.append("<a target=\"_blank\"  href=\"/page/detail/"+pageDetail.getSource()+"/"+pageDetail.getId()+"\" title=\""+pageDetail.getTitle()+"\">"+pageDetail.getTitle()+"</a>");
			jg.writeString(sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
