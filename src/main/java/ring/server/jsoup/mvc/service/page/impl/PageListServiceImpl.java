package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.common.page.common.CommonDetailPage;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.model.page.PageListCounts;
import ring.server.jsoup.mvc.model.page.PageUrl;
import ring.server.jsoup.mvc.service.page.PageListService;

@Service
public class PageListServiceImpl implements PageListService{
	//private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageListMapper pageListMapper;

	@Override
	public List<PageListCounts> findListCounts()throws Exception {
		return pageListMapper.findCounts();
	}
	
	@Override
	public List<PageList> findList(PageList pageList)throws Exception {
		return pageListMapper.findList(pageList);
	}

	@Override
	public PageList findById(String source,String id) throws Exception {
		return pageListMapper.findById(source,id);
	}

	@Autowired
	PageConfigServiceImpl pageConfigServiceImpl;
	@Autowired
	PageUrlServiceImpl  pageUrlServiceImpl;
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	
	
	@Override
	public PageDetail findDetail(String source, String id) throws Exception {
		PageList pageList = this.findById(source,id);
		PageConfig pageConfig = pageConfigServiceImpl.get(source);
		//从page_url获取URL
		//...
		// url = "https://cl.wy8.info/"+pageList.getUrl();
		String url = null;
		List<PageUrl> pageUrls = pageUrlServiceImpl.findByConfigId(pageConfig.getId());
		if(pageUrls!=null&&pageUrls.size()>0){
			//判断是否有效
			//...
			url = pageUrls.get(0).getUrl();
		}
		url +=pageList.getUrl();
		
		return new CommonDetailPage(url, pageConfig, pageDetailServiceImpl).call();
		
	}

}
