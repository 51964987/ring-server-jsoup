package ring.server.jsoup.mvc.service.page.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.mvc.dao.page.PageIndexMapper;
import ring.server.jsoup.mvc.model.page.PageIndex;
import ring.server.jsoup.mvc.service.page.PageIndexService;
@Service
public class PageIndexServiceImpl implements PageIndexService {
	@Autowired
	private PageIndexMapper pageIndexMapper;
	@Override
	public int delete(String id) throws Exception {
		return pageIndexMapper.delete(id);
	}
	@Override
	public int edit(PageIndex pageIndex) throws Exception {
		return pageIndexMapper.edit(pageIndex);
	}
	@Override
	public int save(PageIndex pageIndex) throws Exception {
		return pageIndexMapper.save(pageIndex);
	}
	@Override
	public List<PageIndex> findAll() throws Exception {
		return pageIndexMapper.findAll();
	}
	@Override
	public List<PageIndex> findList(PageIndex pageIndex) throws Exception {
		return pageIndexMapper.findList(pageIndex);
	}

}
