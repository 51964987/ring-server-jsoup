package ring.server.jsoup.common.page.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import ring.server.jsoup.common.page.IListPage;
import ring.server.jsoup.common.util.HttpUrlUtil;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.model.page.PageList;

public class CommonListPage implements Callable<Object>,IListPage{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 分页--当前{page}页面
	 */
	private String url;
	private PageConfig pageConfig;
	private List<PageList> list;
	public CommonListPage(String url,PageConfig pageConfig,List<PageList> list) {
		super();
		this.url = url;
		this.pageConfig = pageConfig;
		this.list = list;
	}

	@Override
	public Object call() throws Exception{

		//Document doc = Jsoup.connect(url).get();
		Document doc = HttpUrlUtil.get(url);
		
		//获取最后一页
		Integer lastPage = 1;
		Element lastATag = doc.getElementById(pageConfig.getLastPageGet());
		String href = lastATag.attr(pageConfig.getLastPageAttr());
		Pattern p1 = Pattern.compile(pageConfig.getLastPagePattern());
		Matcher m1 = p1.matcher(href);
		if(m1.find()){
			lastPage = Integer.valueOf(m1.group(1));
		}
		
		if(list==null){
			list = new ArrayList<>();
		}
		
		Element table = doc.getElementById("ajaxtable");
		if(table != null){			
			Elements trs = table.getElementsByClass("tr3 t_one tac");
			
			Pattern p = Pattern.compile("htm_data/(\\d+)/(\\d+)/(\\d+).html");
			Matcher m = p.matcher("");
			m.find();

			for(int i=0;i<trs.size();i++){
				PageList pageList = new PageList();
				pageList.setTs(new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())));
				Elements tds = trs.get(i).children();
				Element item = tds.get(1).getElementsByTag("h3").get(0).child(0);
				pageList.setTitle(item.text());
				pageList.setUrl(item.attr("href"));
				Element author = tds.get(2).getElementsByTag("a").get(0);
				pageList.setAuthor(author.text());
				pageList.setCreateDate(author.nextElementSibling()!=null?author.nextElementSibling().text():null);
				pageList.setClickNum(tds.get(3).text());
				pageList.setSource(pageConfig.getEnName());
				m.reset(pageList.getUrl());
				if(m.find()){
					pageList.setFid(m.group(1));
					pageList.setYearMonth(m.group(2));
					pageList.setTarget(m.group(3));
					pageList.setId(pageList.getTarget());
				}
				
				if(!StringUtils.isEmpty(pageList.getCreateDate())){					
					Calendar today = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(pageList.getCreateDate().startsWith("今天")){
						today.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH)-1);
						pageList.setCreateDate(sdf.format(today.getTime()));
					}else if(pageList.getCreateDate().startsWith("昨天")){
						today.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH)-2);
						pageList.setCreateDate(sdf.format(today.getTime()));
					}
				}
				
				if(!StringUtils.isEmpty(pageList.getId())){				
					list.add(pageList);
				}
			}
		}else{
			logger.warn("no find table!");
		}
		
		return lastPage;
	}
}
