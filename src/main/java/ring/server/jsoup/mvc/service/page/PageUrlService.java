package ring.server.jsoup.mvc.service.page;

import java.util.List;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageUrl;

public interface PageUrlService {
	int deleteByEnName(String enName)throws RestException;
	int add(List<PageUrl> list)throws RestException;
	List<PageUrl> findByEnName(String enName)throws RestException;
}
