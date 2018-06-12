package ring.server.jsoup.mvc.service.page;

import ring.server.jsoup.mvc.model.page.PageConfig;

public interface PageConfigService {
	int add(PageConfig pageConfig);
	PageConfig get(String enName);
}
