package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageModel;

@Mapper
public interface PageModelMapper {
	List<PageModel> findBySource(String source)throws Exception;
}
