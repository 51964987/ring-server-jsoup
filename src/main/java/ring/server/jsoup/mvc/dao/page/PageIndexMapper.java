package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageIndex;

@Mapper
public interface PageIndexMapper {
	PageIndex findByEnName(String enName)throws Exception;
	int delete(String enName) throws Exception;
	int edit(PageIndex pageIndex) throws Exception;
	int save(PageIndex pageIndex) throws Exception;
	List<PageIndex> findAll()throws Exception;
	List<PageIndex> findList(PageIndex pageIndex)throws Exception;
}
