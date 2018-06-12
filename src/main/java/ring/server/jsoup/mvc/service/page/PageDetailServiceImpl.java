package ring.server.jsoup.mvc.service.page;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.server.jsoup.mvc.dao.page.PageDetailMapper;
import ring.server.jsoup.mvc.model.page.PageDetail;
@Service
public class PageDetailServiceImpl implements PageDetailService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PageDetailMapper pageDetailMapper;
	
	@Override
	public int add(PageDetail pageDetail) {
		try {
			pageDetail.setId(UUID.randomUUID().toString());
			pageDetailMapper.add(pageDetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return 0;
	}

}
