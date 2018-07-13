package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageUrl;

@Mapper
public interface PageUrlMapper {
	int add(List<PageUrl> list)throws Exception;
	List<PageUrl> findByConfigId(String enName)throws Exception;
}
