package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.mvc.model.page.PageIndex;

public interface PageIndexService {
	List<PageIndex> findAll()throws Exception;
	List<PageIndex> findList(PageIndex pageIndex)throws Exception;
}
