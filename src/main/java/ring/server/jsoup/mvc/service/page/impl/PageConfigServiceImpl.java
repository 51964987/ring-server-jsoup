package ring.server.jsoup.mvc.service.page.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.mvc.dao.page.PageCofigMapper;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.service.page.PageConfigService;
@Service
public class PageConfigServiceImpl implements PageConfigService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageCofigMapper pageCofigMapper;
	
	@Override
	public int add(PageConfig pageConfig) {
		try {
			pageConfig.setId(UUID.randomUUID().toString());
			pageCofigMapper.add(pageConfig);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return 0;
	}

	@Override
	public PageConfig get(String enName) {
		try {
			return pageCofigMapper.get(enName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return null;
	}

}
