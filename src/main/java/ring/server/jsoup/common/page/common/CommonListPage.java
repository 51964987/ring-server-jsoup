package ring.server.jsoup.common.page.common;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import ring.server.jsoup.common.page.IListPage;
import ring.server.jsoup.mvc.model.page.PageList;

public class CommonListPage implements Callable<Object>,IListPage{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 分页--当前{page}页面
	 */
	private String url;
	private List<PageList> list;
	public CommonListPage(String url,List<PageList> list) {
		super();
		this.url = url;
		this.list = list;
	}

	@Override
	public Object call() throws Exception{

    	Document doc = null;
		boolean timeout = false;
		int readNum=0;
		
		do{				
			try {
				doc = Jsoup.connect(url ).get();
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
		//System.out.println(doc);
		Element table = doc.getElementById("ajaxtable");
		if(table != null){			
			Elements trs = table.getElementsByClass("tr3 t_one tac");
			
			Pattern p = Pattern.compile("htm_data/(\\d+)/(\\d+)/(\\d+).html");
			Matcher m = p.matcher("");
			m.find();
			//System.out.println("trs======"+trs.size());
			for(int i=0;i<trs.size();i++){
				PageList pageList = new PageList();
				//pageList.setId(UUID.randomUUID().toString());
				Elements tds = trs.get(i).children();
				Element item = tds.get(1).getElementsByTag("h3").get(0).child(0);
				pageList.setTitle(item.text());
				//System.out.println(pageList.getTitle());
				pageList.setUrl(item.attr("href"));
				Element author = tds.get(2).getElementsByTag("a").get(0);
				pageList.setAuthor(author.text());
				pageList.setCreateDate(author.nextElementSibling()!=null?author.nextElementSibling().text():null);
				pageList.setClickNum(tds.get(3).text());
				
				m.reset(pageList.getUrl());
				if(m.find()){
					pageList.setFid(m.group(1));
					pageList.setYearMonth(m.group(2));
					pageList.setTarget(m.group(3));
					pageList.setId(pageList.getTarget());
				}
				
				if(!StringUtils.isEmpty(pageList.getId())){				
					list.add(pageList);
				}
			}
		}else{
			logger.warn("no find table!");
		}
				
		return null;
	}
}
