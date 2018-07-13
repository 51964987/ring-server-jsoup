package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageDetail;

public interface PageDetailService {
	int add(PageDetail pageDetail) throws RestException;
	int delete(String source,String id) throws RestException;
	PageDetail findById(String source,String id) throws RestException;
	List<PageDetail> findList(PageDetail pageDetail)throws RestException;
}
