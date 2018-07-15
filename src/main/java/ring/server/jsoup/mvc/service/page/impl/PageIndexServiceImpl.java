package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.common.rest.RestCode;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.page.PageIndexMapper;
import ring.server.jsoup.mvc.model.page.PageIndex;
import ring.server.jsoup.mvc.service.page.PageIndexService;
@Service
public class PageIndexServiceImpl implements PageIndexService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageIndexMapper pageIndexMapper;
	
	@Override
	public PageIndex findByEnName(String enName) throws RestException {
		try {
			return pageIndexMapper.findByEnName(enName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}
	
	@Override
	public int delete(String enName) throws RestException {
		try {
			return pageIndexMapper.delete(enName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}
	@Override
	public int edit(PageIndex pageIndex) throws RestException {
		try {
			return pageIndexMapper.edit(pageIndex);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}
	@Override
	public int save(PageIndex pageIndex) throws RestException {
		try {
			return pageIndexMapper.save(pageIndex);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}
	@Override
	public List<PageIndex> findAll() throws RestException {
		try {
			return pageIndexMapper.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}
	@Override
	public List<PageIndex> findList(PageIndex pageIndex) throws RestException {
		try {
			return pageIndexMapper.findList(pageIndex);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new RestException(RestCode.DATATABLE_ERROR,e);
		}
	}

}
