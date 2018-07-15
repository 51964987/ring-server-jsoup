package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageUrl;

@Mapper
public interface PageUrlMapper {
	int deleteByEnName(String enName)throws Exception;
	int add(List<PageUrl> list)throws Exception;
	List<PageUrl> findByEnName(String enName)throws Exception;
}
