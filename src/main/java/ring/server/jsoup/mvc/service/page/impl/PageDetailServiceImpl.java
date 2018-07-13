package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.common.rest.RestCode;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.page.PageDetailMapper;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.service.page.PageDetailService;
@Service
public class PageDetailServiceImpl implements PageDetailService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageDetailMapper pageDetailMapper;
	
	@Override
	public int add(PageDetail pageDetail) {
		try {
			pageDetailMapper.add(pageDetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return 0;
	}

	@Override
	public List<PageDetail> findList(PageDetail pageDetail) throws RestException {
		try {
			return pageDetailMapper.findList(pageDetail);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR);
		}
	}

	@Override
	public PageDetail findById(String source, String id) throws RestException {
		try {
			return pageDetailMapper.findById(source, id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR);
		}
	}

	@Override
	public int delete(String source, String id) throws RestException {
		try {
			return pageDetailMapper.delete(source, id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR);
		}
	}

}
