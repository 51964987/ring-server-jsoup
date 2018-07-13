package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.model.page.PageListCounts;

public interface PageListService {
	PageDetail findDetail(String source,String id)throws RestException;
	PageList findById(String source,String id)throws RestException;
	List<PageList> findList(PageList pageList)throws RestException;
	List<PageListCounts> findListCounts()throws RestException;
}
