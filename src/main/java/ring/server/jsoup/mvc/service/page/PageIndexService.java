package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageIndex;

public interface PageIndexService {
	int delete(String id)throws RestException;
	int edit(PageIndex pageIndex)throws RestException;
	int save(PageIndex pageIndex)throws RestException;
	List<PageIndex> findAll()throws RestException;
	List<PageIndex> findList(PageIndex pageIndex)throws RestException;
}
