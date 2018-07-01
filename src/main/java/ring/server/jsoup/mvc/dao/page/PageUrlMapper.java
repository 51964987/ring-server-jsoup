package ring.server.jsoup.mvc.dao.page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageUrl;

@Mapper
public interface PageUrlMapper {
	List<PageUrl> findByConfigId(String enName)throws Exception;
}
