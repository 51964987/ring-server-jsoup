package ring.server.jsoup.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ring.server.jsoup.common.rest.RestCode;
import ring.server.jsoup.common.rest.RestException;

public class HttpUrlUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUrlUtil.class);
	
	public static Document get(String url) throws RestException{
		Document doc = null;
		boolean timeout = false;
		int readNum=1;
		
		do{				
			try {
				logger.info("请次"+readNum+"请求-->"+url);
				doc = Jsoup.connect(url).get();
				timeout = false;
				if(readNum==10){
					break;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				timeout = true;
				readNum++;
			}
		}while(timeout);
		
		if(timeout){
			throw new RestException(RestCode.TIMEOUT_ERROR,url);
		}
		
		return doc;
	}
}
