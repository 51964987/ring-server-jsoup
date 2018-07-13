package ring.server.jsoup.mvc.service.page;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageConfig;

public interface PageConfigService {
	void add(PageConfig pageConfig) throws RestException;
	PageConfig get(String enName) throws RestException;
}
