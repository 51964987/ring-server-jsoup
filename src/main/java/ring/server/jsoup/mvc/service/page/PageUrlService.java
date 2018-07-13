package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageUrl;

public interface PageUrlService {
	int add(List<PageUrl> list)throws RestException;
	List<PageUrl> findByConfigId(String configIp)throws RestException;
}
