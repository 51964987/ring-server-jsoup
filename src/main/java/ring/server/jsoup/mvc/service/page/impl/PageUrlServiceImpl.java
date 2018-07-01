package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.mvc.dao.page.PageUrlMapper;
import ring.server.jsoup.mvc.model.page.PageUrl;
import ring.server.jsoup.mvc.service.page.PageUrlService;
@Service
public class PageUrlServiceImpl implements PageUrlService {
	@Autowired
	private PageUrlMapper pageUrlMapper;
	
	@Override
	public List<PageUrl> findByConfigId(String configIp) throws Exception {
		return pageUrlMapper.findByConfigId(configIp);
	}

}
