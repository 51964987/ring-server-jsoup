package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.model.page.PageListCounts;

@Mapper
public interface PageListMapper {
	int addList(List<PageList> list)throws Exception;
	PageList findById(String id)throws Exception;
	List<PageList> findList(PageList pageList)throws Exception;
	List<PageListCounts> findCounts()throws Exception;
}
