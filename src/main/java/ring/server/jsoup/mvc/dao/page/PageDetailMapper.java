package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ring.server.jsoup.mvc.model.page.PageDetail;

@Mapper
public interface PageDetailMapper {
	int add(PageDetail pageDetail)throws Exception;
	PageDetail findById(@Param("source")String source,@Param("id")String id)throws Exception;
	List<PageDetail> findList(PageDetail pageDetail)throws Exception;
}
