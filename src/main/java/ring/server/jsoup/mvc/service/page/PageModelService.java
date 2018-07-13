package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageModel;

public interface PageModelService {
	List<PageModel> findBySource(String source)throws RestException;
}
