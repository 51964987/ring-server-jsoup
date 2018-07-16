package ring.server.jsoup.common.page.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ring.server.jsoup.common.page.IPagination;
import ring.server.jsoup.common.util.HttpUrlUtil;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.config.PageListConfig;
import ring.server.jsoup.mvc.model.page.PageList;

public class CommonPagination implements IPagination{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String url;
	private PageListConfig pageListConfig;
	private PageListMapper pageListMapper;
	
	public CommonPagination(String url, PageListConfig pageListConfig,PageListMapper pageListMapper) {
		super();
		this.url = url;
		this.pageListConfig = pageListConfig;
		this.pageListMapper = pageListMapper;
	}

	@Override
	public void call(){
		try {
			//获取配置信息
			//PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
			
			//获取最后一页
			//Document doc = Jsoup.connect(url).get();
			Document doc = HttpUrlUtil.get(url);
			
			Element lastATag = doc.getElementById(pageListConfig.getLastPageGet());
			String href = lastATag.attr(pageListConfig.getLastPageAttr());
			Pattern p = Pattern.compile(pageListConfig.getLastPagePattern());
			Matcher m = p.matcher(href);
			Integer lastPage = 1;
			if(m.find()){
				lastPage = Integer.valueOf(m.group(1));
				logger.info("the last page is "+lastPage);
			}
			
			if(lastPage>100){
				lastPage = 100;
			}
			
			//遍历每一页面
			StringBuffer urlSb = new StringBuffer();
			List<PageList> list = new ArrayList<>();
			for(int i=1;i<=lastPage;i++){
				urlSb.setLength(0);
				m.reset(url);
				if(m.find()){
					m.appendReplacement(urlSb, "page="+i);
				}
				new CommonListPage(urlSb.toString(),pageListConfig,list).call();
				if(list.size()>0){
					logger.info("批量插入数据"+list.size()+"条");
					pageListMapper.addList(list);
					list.clear();
				}
				//睡眠10秒钟
				logger.info("睡眠前时间");
				Thread.sleep(4*1000);
				logger.info("睡眠后时间");
			}
			
			if(list.size()>0){
				logger.info("批量插入数据"+list.size()+"条");
				pageListMapper.addList(list);
				list.clear();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
