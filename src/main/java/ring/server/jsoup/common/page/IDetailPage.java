package ring.server.jsoup.common.page;

import ring.server.jsoup.mvc.model.page.PageDetail;

public interface IDetailPage {
	PageDetail call()throws Exception;
}
