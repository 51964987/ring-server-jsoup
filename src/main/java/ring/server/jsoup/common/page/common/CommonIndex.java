package ring.server.jsoup.common.page.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ring.server.jsoup.common.page.IPagination;
import ring.server.jsoup.common.util.HttpUrlUtil;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.config.PageListConfig;

public class CommonIndex implements IPagination{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String url;
	private PageListConfig pageListConfig;
	private PageListMapper pageListMapper;
	
	public CommonIndex(String url, PageListConfig pageListConfig,PageListMapper pageListMapper) {
		super();
		this.url = url;
		this.pageListConfig = pageListConfig;
		this.pageListMapper = pageListMapper;
	}

	@Override
	public void call(){
		try {
			
			//获取最后一页
			Pattern p = Pattern.compile("https?\\://[\\w+\\.]+/");
			Matcher m = p.matcher(url);
			String rootUrl = "";
			if(m.find()){
				rootUrl=m.group();
			}
			
			Document doc = HttpUrlUtil.get(url);
			
			Elements paginations = doc.getElementsByTag("h2");
			if(paginations!=null&&paginations.size()>0){
				for(int i=paginations.size()-1;i>=0;i--){
					String url = paginations.get(i).child(0).attr("href");
					if(url.startsWith("thread0806")){
						url=rootUrl+url+"&search=&page=1";
						new CommonPagination(url,pageListConfig,pageListMapper).call();
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
