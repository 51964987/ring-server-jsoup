package ring.server.jsoup.mvc.dao.page;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageDetail;

@Mapper
public interface PageDetailMapper {
	int add(PageDetail pageDetail)throws Exception;
}
