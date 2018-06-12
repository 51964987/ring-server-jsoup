package ring.server.jsoup.mvc.dao.page;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.page.PageConfig;

@Mapper
public interface PageCofigMapper {
	int add(PageConfig pageConfig)throws Exception;
	PageConfig get(String enName)throws Exception;
}
