package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.mvc.model.page.PageList;

public interface PageListService {
	PageList findById(String id)throws Exception;
	List<PageList> findList(PageList pageList)throws Exception;
}
