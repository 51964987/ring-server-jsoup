package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.mvc.dao.page.PageModelMapper;
import ring.server.jsoup.mvc.model.page.PageModel;
import ring.server.jsoup.mvc.service.page.PageModelService;
@Service
public class PageModelServiceImpl implements PageModelService {
	@Autowired
	private PageModelMapper pageModelMapper;
	@Override
	public List<PageModel> findBySource(String source) throws Exception {
		return pageModelMapper.findBySource(source);
	}

}
