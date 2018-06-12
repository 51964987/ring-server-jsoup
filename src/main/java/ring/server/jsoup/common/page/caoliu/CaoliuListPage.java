package ring.server.jsoup.common.page.caoliu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ring.server.jsoup.common.page.IListPage;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.service.page.PageDetailServiceImpl;

public class CaoliuListPage implements Callable<PageDetail>,IListPage{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 分页--当前页面
	 */
	private String url;
	private PageDetailServiceImpl pageDetailServiceImpl;
	
	public CaoliuListPage(String url,PageDetailServiceImpl pageDetailServiceImpl) {
		super();
		this.url = url;
		this.pageDetailServiceImpl = pageDetailServiceImpl;
	}

	@Override
	public PageDetail call() throws Exception{
		
		//--后续从数据读取
		PageConfig pageConfig = new PageConfig();
		pageConfig.setDownload(false);
		pageConfig.setEnName("T66Y");
		
    	//1.域名
    	String rootPath = "";
    	String fid = "";
    	String page = "";
    	Pattern p = Pattern.compile("(https?://[[\\d\\w]+\\.]+/)thread0806.php\\?fid=(\\d+)&search=&page=(\\d+)");
    	Matcher m = p.matcher(url);
    	if(m.find()){
    		rootPath = m.group(1);
    		fid = m.group(2);
    		page = m.group(3);
    	}
    	
		//2.请求
		Document doc = Jsoup.connect(url).get();
		Elements h3s = doc.getElementsByTag("h3");
		ExecutorService pool = Executors.newFixedThreadPool(h3s.size()>5?5:h3s.size());  
		List<Future<PageDetail>> list = new ArrayList<Future<PageDetail>>();  
		for(int i=0;i<h3s.size();i++){
			Elements as = h3s.get(i).getElementsByTag("a").get(0).getElementsByAttributeValueEnding("href", "html");
			if(as!=null&&as.size()>0){
				list.add(pool.submit(new CaoliuDetailPage(fid, page, 
						rootPath+as.get(0).attr("href"),as.get(0).text(),
						pageConfig,
						pageDetailServiceImpl)));
			}
		}

		// 关闭线程池  
		pool.shutdown();  
		
		// 获取所有并发任务的运行结果  
		for (Future<PageDetail> f : list) {  
			// 从Future对象上获取任务的返回值，并输出到控制台  
			PageDetail result = f.get();
			if(result!=null&&"-1".equals(result.getCode())){
				logger.error(result.getMessage());
			}
		}
		
		return null;
	}
}
