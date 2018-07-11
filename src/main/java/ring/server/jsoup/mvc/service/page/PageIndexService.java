package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.mvc.model.page.PageIndex;

public interface PageIndexService {
	int delete(String id)throws Exception;
	int edit(PageIndex pageIndex)throws Exception;
	int save(PageIndex pageIndex)throws Exception;
	List<PageIndex> findAll()throws Exception;
	List<PageIndex> findList(PageIndex pageIndex)throws Exception;
}
