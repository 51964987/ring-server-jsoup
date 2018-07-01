package ring.server.jsoup.mvc.service.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.model.page.PageListCounts;

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
	public PageList findById(String id) throws Exception {
		return pageListMapper.findById(id);
	}

}
