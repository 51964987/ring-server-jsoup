package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.common.rest.RestCode;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.page.PageModelMapper;
import ring.server.jsoup.mvc.model.page.PageModel;
import ring.server.jsoup.mvc.service.page.PageModelService;
@Service
public class PageModelServiceImpl implements PageModelService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageModelMapper pageModelMapper;
	@Override
	public List<PageModel> findBySource(String source) throws RestException {
		try {
			return pageModelMapper.findBySource(source);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR);
		}
	}

}
