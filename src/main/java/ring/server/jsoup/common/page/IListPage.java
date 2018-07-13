package ring.server.jsoup.common.page;

import ring.server.jsoup.common.rest.RestException;

public interface IListPage {
	Object call()throws RestException;
}
