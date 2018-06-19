package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageList;

@Mapper
public interface PageListMapper {
	int addList(List<PageList> list)throws Exception;
	int addTempTable()throws Exception;
	int addByTempTable()throws Exception;
	int updateByTempTable()throws Exception;
}
