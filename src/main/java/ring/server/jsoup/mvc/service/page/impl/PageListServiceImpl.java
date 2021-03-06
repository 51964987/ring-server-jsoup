package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.common.page.common.CommonDetailPage;
import ring.server.jsoup.common.rest.RestCode;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.config.PageListConfig;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.model.page.PageListCounts;
import ring.server.jsoup.mvc.model.page.PageUrl;
import ring.server.jsoup.mvc.service.config.impl.PageListConfigServiceImpl;
import ring.server.jsoup.mvc.service.page.PageListService;

@Service
public class PageListServiceImpl implements PageListService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageListMapper pageListMapper;

	@Override
	public List<PageListCounts> findListCounts()throws RestException {
		try {
			return pageListMapper.findCounts();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}
	
	@Override
	public List<PageList> findList(PageList pageList)throws RestException {
		try {
			return pageListMapper.findList(pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

	@Override
	public PageList findById(String source,String id) throws RestException {
		try {
			return pageListMapper.findById(source,id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

	/*@Autowired
	PageConfigServiceImpl pageConfigServiceImpl;*/
	@Autowired
	PageListConfigServiceImpl pageListConfigServiceImpl;
	@Autowired
	PageUrlServiceImpl  pageUrlServiceImpl;
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	
	
	@Override
	public PageDetail findDetail(String source, String id) throws RestException {
		try {
			PageList pageList = this.findById(source,id);
			PageListConfig pageListConfig = pageListConfigServiceImpl.findById(source);
			//从page_url获取URL
			//...
			// url = "https://cl.wy8.info/"+pageList.getUrl();
			String url = null;
			List<PageUrl> pageUrls = pageUrlServiceImpl.findByEnName(pageListConfig.getId());
			if(pageUrls!=null&&pageUrls.size()>0){
				//判断是否有效
				//...
				url = pageUrls.get(0).getUrl();
			}
			url +=pageList.getUrl();
			
			return new CommonDetailPage(url, pageListConfig, pageDetailServiceImpl).call();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
		
	}

}
