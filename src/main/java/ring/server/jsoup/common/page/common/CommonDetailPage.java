package ring.server.jsoup.common.page.common;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import ring.server.jsoup.common.page.IDetailPage;
import ring.server.jsoup.common.util.DownloadUtil;
import ring.server.jsoup.common.util.HttpUrlUtil;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.service.page.impl.PageDetailServiceImpl;

/**
 * 详细页面 
 * @author ring
 * @date 2018年6月3日 下午9:38:05
 * @version V1.0
 */
public class CommonDetailPage implements Callable<PageDetail>,IDetailPage {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String fid;
	private String curpage;
	private String tid;
	private String url;
	private String title;
	private PageConfig pageConfig;
	private PageDetailServiceImpl pageDetailServiceImpl;
	
	public CommonDetailPage(String fid, String curpage,String tid, String url,String title,
			PageConfig pageConfig,PageDetailServiceImpl pageDetailServiceImpl) {
		super();
		this.fid = fid;
		this.curpage = curpage;
		this.tid = tid;
		this.url = url;
		this.title = title;
		this.pageConfig = pageConfig;
		this.pageDetailServiceImpl = pageDetailServiceImpl;
	}

	public CommonDetailPage(String url,PageConfig pageConfig,PageDetailServiceImpl pageDetailServiceImpl) {
		super();
		this.url = url;
		this.fid = "0";
		this.curpage = "0";
		this.url = url;
		this.pageConfig = pageConfig;
		this.pageDetailServiceImpl = pageDetailServiceImpl;
	}

	@Override
	public PageDetail call() throws Exception {
		
		PageDetail pageDetail = new PageDetail();
		pageDetail.setFid(fid);
		pageDetail.setCurpage(curpage);
		pageDetail.setSource(pageConfig.getEnName());
		
		//线程名称
		Thread.currentThread().setName("FID_"+fid+"_PAGE_"+curpage+"_TID="+tid);
		logger.info("开始执行，"+url);

		try {
			
	    	if(StringUtils.isEmpty(pageConfig.getDetailUrlPattern())){
	    		throw new Exception("detailUrlPattern is null");
	    	}
	    	if(StringUtils.isEmpty(pageConfig.getImageGet())){
	    		throw new Exception("imageGet is null");
	    	}
	    	if(StringUtils.isEmpty(pageConfig.getImageAttr())){
	    		throw new Exception("imageAttr is null");
	    	}
	    	if(StringUtils.isEmpty(pageConfig.getMagnetGet())){
	    		throw new Exception("magnetGet is null");
	    	}
			
			//2.请求
			Document doc = HttpUrlUtil.get(url);
			//pageDetail.setContent(doc.getElementsByTag("body").outerHtml());
			Elements ths = doc.getElementsByTag("th");
			if(ths!=null&&ths.size()>0){
				StringBuffer outerHtmlSb = new StringBuffer();
				for(int i=0;i<ths.size();i++){
					Elements content = ths.get(i).getElementsByClass("tpc_content");
					if(content!=null){
						outerHtmlSb.append(content.outerHtml());
					}
				}
				pageDetail.setContent(outerHtmlSb.toString());
			}
			
			//1.来源页面
			String targetFold = "";
			Pattern p = Pattern.compile(pageConfig.getDetailUrlPattern());
			Matcher m = p.matcher(url);
			
			if(m.find()){
				targetFold = m.group(1);
				pageDetail.setUrl(m.group());
				pageDetail.setSort(m.group(2));
			}
			if(StringUtils.isEmpty(title)){
				title = doc.getElementsByTag("title").text().replace(" ", "").split("-")[0];
			}
			pageDetail.setTitle(title);
			
			//指定下载目录
			String rootFile = null;
			if(StringUtils.isEmpty(pageConfig.getLocalpath())){				
				//默认最后一个盘符
				File[] roots = File.listRoots();
				for(int i=roots.length-1;i>0;i--){
					if(roots[i].exists()&&roots[i].getFreeSpace()>0){
						rootFile = roots[i].getPath()+"temp\\";
						break;
					}
				}
			}else{
				rootFile = pageConfig.getLocalpath();
			}
			
			String outputpath = rootFile+targetFold+(StringUtils.isEmpty(pageDetail.getSort())?"":"\\"+pageDetail.getSort()+"\\")+"\\";//+title+"\\";
			
			//3.图片下载
			Elements imgs = doc.getElementsByAttribute(pageConfig.getImageGet());
			DownloadUtil jsoupUtil = new DownloadUtil();
			String images = jsoupUtil.imagesText(imgs, pageConfig.getImageAttr(), outputpath,pageConfig.isDownload() );
			pageDetail.setImages(images);
			
			//4.磁力链接下载
			Elements as = doc.getElementsByTag(pageConfig.getMagnetGet());
			String magnet = jsoupUtil.elementsText(as, outputpath+"\\magnet.txt",Pattern.compile("magnet.*"),pageConfig.isDownload());
			pageDetail.setMagnet(magnet);
			
			//种子链接下载
			Elements torrentAs = doc.getElementsByTag(pageConfig.getTorrentGet());
			String torrent = jsoupUtil.elementsText(torrentAs, outputpath+"\\torrent.txt",Pattern.compile("http\\://www\\.viidii\\.info.*"),pageConfig.isDownload());
			pageDetail.setTorrent(torrent);
			
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
