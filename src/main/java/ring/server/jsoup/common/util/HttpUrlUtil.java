package ring.server.jsoup.common.util;

import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUrlUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUrlUtil.class);
	
	public static Document get(String url) throws Exception{
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
			} catch (SocketTimeoutException e) {
				logger.error(e.getMessage(),e);
				timeout = true;
				readNum++;
			}
		}while(timeout);
		
		if(timeout){
			throw new Exception("请求超时="+url);
		}
		
		return doc;
	}
}
