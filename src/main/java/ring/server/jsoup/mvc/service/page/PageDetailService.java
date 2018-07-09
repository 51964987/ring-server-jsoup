package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.mvc.model.page.PageDetail;

public interface PageDetailService {
	int add(PageDetail pageDetail);
	PageDetail findById(String source,String id) throws Exception;
	List<PageDetail> findList(PageDetail pageDetail)throws Exception;
}
