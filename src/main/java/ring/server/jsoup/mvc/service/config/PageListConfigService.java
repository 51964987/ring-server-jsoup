package ring.server.jsoup.mvc.service.config;

import java.util.List;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.config.PageListConfig;

public interface PageListConfigService {
	PageListConfig findById(String id)throws RestException;
	int delete(String id)throws RestException;
	int edit(PageListConfig pageListConfig)throws RestException;
	int save(PageListConfig pageListConfig)throws RestException;
	List<PageListConfig> findList(PageListConfig pageListConfig)throws RestException;
}
