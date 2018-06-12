package ring.server.jsoup.common.page.caoliu;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import ring.server.jsoup.common.page.IDetailPage;
import ring.server.jsoup.common.util.DownloadUtil;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.service.page.PageDetailServiceImpl;

/**
 * 详细页面 
 * @author ring
 * @date 2018年6月3日 下午9:38:05
 * @version V1.0
 */
public class CaoliuDetailPage implements Callable<PageDetail>,IDetailPage {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String tid;
	private String curpage;
	private String url;
	private String title;
	private PageConfig pageConfig;
	private PageDetailServiceImpl pageDetailServiceImpl;
	
	public CaoliuDetailPage(String tid, String curpage, String url,String title,
			PageConfig pageConfig,PageDetailServiceImpl pageDetailServiceImpl) {
		super();
		this.tid = tid;
		this.curpage = curpage;
		this.url = url;
		this.title = title;
		this.pageConfig = pageConfig;
		this.pageDetailServiceImpl = pageDetailServiceImpl;
	}

	public CaoliuDetailPage(String url,PageConfig pageConfig,PageDetailServiceImpl pageDetailServiceImpl) {
		super();
		this.url = url;
		this.tid = "0";
		this.curpage = "0";
		this.url = url;
		this.pageConfig = pageConfig;
		this.pageDetailServiceImpl = pageDetailServiceImpl;
	}

	@Override
	public PageDetail call() throws Exception {
		
		PageDetail pageDetail = new PageDetail();
		pageDetail.setFid(tid);
		pageDetail.setCurpage(curpage);
		pageDetail.setSource(pageConfig.getEnName());
		
		//线程名称
		Thread.currentThread().setName("THREAD_"+tid+"_DETAIL_PAGE_"+curpage);
		logger.info("开始执行，"+url);

		try {
			
			//2.请求
			Document doc = Jsoup.connect(url).get();
			pageDetail.setContent(doc.getElementsByTag("body").outerHtml());
			
			//1.来源页面
			String targetFold = "";
			Pattern p = Pattern.compile("(htm_data/\\d+/\\d+/\\d+).html");
			Matcher m = p.matcher(url);
			
			if(m.find()){
				targetFold = m.group(1);
				pageDetail.setUrl(m.group());
			}
			if(StringUtils.isEmpty(title)){
				title = doc.getElementsByTag("title").text().replace(" ", "").split("-")[0];
			}
			pageDetail.setTitle(title);
			
			//默认最后一个盘符
			File[] roots = File.listRoots();
			String rootFile = null;
			for(int i=roots.length-1;i>0;i--){
				if(roots[i].exists()&&roots[i].getFreeSpace()>0){
					rootFile = roots[i].getPath();
				}
			}
			String outputpath = rootFile+"temp\\"+targetFold+"\\"+title+"\\";
			
			//3.图片下载
			Elements imgs = doc.getElementsByAttribute("data-src");
			DownloadUtil jsoupUtil = new DownloadUtil();
			String images = jsoupUtil.imagesText(imgs, "data-src", outputpath,pageConfig.isDownload() );
			pageDetail.setImages(images);
			
			//4.磁力链接下载
			Elements as = doc.getElementsByTag("a");
			String magnet = jsoupUtil.magnetText(as, outputpath+"\\magnet.txt",pageConfig.isDownload());
			pageDetail.setMagnet(magnet);
			
			//保存到数据库
			pageDetailServiceImpl.add(pageDetail);
			pageDetail.setCode("200");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			pageDetail.setCode("-1");
			pageDetail.setMessage(e.getMessage());
		}
		
		return pageDetail;
	}

}
