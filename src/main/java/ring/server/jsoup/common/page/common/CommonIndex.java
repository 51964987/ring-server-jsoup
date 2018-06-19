package ring.server.jsoup.common.page.common;

import java.net.SocketTimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ring.server.jsoup.common.page.IPagination;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.service.page.PageConfigServiceImpl;

public class CommonIndex implements IPagination{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String url;
	private PageConfigServiceImpl pageConfigServiceImpl;
	private PageListMapper pageListMapper;
	
	public CommonIndex(String url, PageConfigServiceImpl pageConfigServiceImpl,
			PageListMapper pageListMapper) {
		super();
		this.url = url;
		this.pageConfigServiceImpl = pageConfigServiceImpl;
		this.pageListMapper = pageListMapper;
	}

	@Override
	public void call(){
		try {
			
			//获取最后一页
			Document doc = null;
			boolean timeout = false;
			int readNum=0;
			
			Pattern p = Pattern.compile("https?\\://[\\w+\\.]+/");
			Matcher m = p.matcher(url);
			String rootUrl = "";
			if(m.find()){
				rootUrl=m.group();
			}
			
			do{				
				try {
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
			
			Elements paginations = doc.getElementsByTag("h2");
			if(paginations!=null&&paginations.size()>0){
				for(int i=0;i<paginations.size();i++){
					String url = paginations.get(i).child(0).attr("href");
					if(url.startsWith("thread0806")){
						url=rootUrl+url+"&search=&page=1";
						new CommonPagination(url,pageConfigServiceImpl,pageListMapper).call();
					}
				}
			}else{
				logger.warn("no find Pagination");
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
