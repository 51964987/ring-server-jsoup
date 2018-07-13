package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.common.rest.RestCode;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.page.PageUrlMapper;
import ring.server.jsoup.mvc.model.page.PageUrl;
import ring.server.jsoup.mvc.service.page.PageUrlService;
@Service
public class PageUrlServiceImpl implements PageUrlService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageUrlMapper pageUrlMapper;
	
	@Override
	public List<PageUrl> findByConfigId(String configIp) throws RestException {
		try {
			return pageUrlMapper.findByConfigId(configIp);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR);
		}
	}

	@Override
	public int add(List<PageUrl> list) throws RestException {
		try {
			return pageUrlMapper.add(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR);
		}
	}

}
