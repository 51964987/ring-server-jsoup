package ring.server.jsoup.common.page.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ring.server.jsoup.common.page.IPagination;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.service.page.PageConfigServiceImpl;
import ring.server.jsoup.mvc.service.page.PageDetailServiceImpl;

public class CommonPagination implements IPagination{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String url;
	private PageConfigServiceImpl pageConfigServiceImpl;
	private PageDetailServiceImpl pageDetailServiceImpl;
	
	public CommonPagination(String url, PageConfigServiceImpl pageConfigServiceImpl,
			PageDetailServiceImpl pageDetailServiceImpl) {
		super();
		this.url = url;
		this.pageConfigServiceImpl = pageConfigServiceImpl;
		this.pageDetailServiceImpl = pageDetailServiceImpl;
	}

	@Override
	public void call(){
		try {
			//获取配置信息
			PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
//			pageConfig.setLastPageGet("last");
//			pageConfig.setLastPageAttr("href");
//			pageConfig.setLastPagePattern("page\\=(\\d+)");
			
			//获取最后一页
			Document doc = Jsoup.connect(url).get();
			Element lastATag = doc.getElementById(pageConfig.getLastPageGet());
			String href = lastATag.attr(pageConfig.getLastPageAttr());
			Pattern p = Pattern.compile(pageConfig.getLastPagePattern());
			Matcher m = p.matcher(href);
			Integer lastPage = 1;
			if(m.find()){
				lastPage = Integer.valueOf(m.group(1));
				logger.info("the last page is "+lastPage);
			}
			
			//遍历每一页面
			StringBuffer urlSb = new StringBuffer();
			for(int i=1;i<=lastPage;i++){
				urlSb.setLength(0);
				m.reset(url);
				if(m.find()){
					m.appendReplacement(urlSb, "page="+i);
				}
				System.out.println(urlSb);
				new CommonDetailPage(urlSb.toString(),pageConfig,pageDetailServiceImpl).call();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
