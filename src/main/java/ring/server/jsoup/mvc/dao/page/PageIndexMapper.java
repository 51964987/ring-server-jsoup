package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageIndex;

@Mapper
public interface PageIndexMapper {
	List<PageIndex> findAll()throws Exception;
}
