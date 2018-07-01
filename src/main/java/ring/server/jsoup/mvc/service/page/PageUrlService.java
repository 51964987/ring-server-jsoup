package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.mvc.model.page.PageUrl;

public interface PageUrlService {
	List<PageUrl> findByConfigId(String configIp)throws Exception;
}
