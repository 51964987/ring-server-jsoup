package ring.server.jsoup.mvc.service.config.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.common.rest.RestCode;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.config.PageListConfigMapper;
import ring.server.jsoup.mvc.model.config.PageListConfig;
import ring.server.jsoup.mvc.service.config.PageListConfigService;
@Service
public class PageListConfigServiceImpl implements PageListConfigService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageListConfigMapper pageListConfigMapper;
	@Override
	public PageListConfig findById(String id) throws RestException {
		try {
			return pageListConfigMapper.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

	@Override
	public int delete(String enName) throws RestException {
		try {
			return pageListConfigMapper.delete(enName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

	@Override
	public int edit(PageListConfig pageListConfig) throws RestException {
		try {
			return pageListConfigMapper.edit(pageListConfig);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

	@Override
	public int save(PageListConfig pageListConfig) throws RestException {
		try {
			return pageListConfigMapper.save(pageListConfig);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

	@Override
	public List<PageListConfig> findList(PageListConfig pageListConfig) throws RestException {
		try {
			return pageListConfigMapper.findList(pageListConfig);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

}
