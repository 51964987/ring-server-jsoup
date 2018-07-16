package ring.server.jsoup.mvc.dao.config;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ring.server.jsoup.mvc.model.config.PageListConfig;

@Mapper
public interface PageListConfigMapper {
	PageListConfig findById(String id)throws Exception;
	int delete(String id) throws Exception;
	int edit(PageListConfig pageListConfig) throws Exception;
	int save(PageListConfig pageListConfig) throws Exception;
	List<PageListConfig> findList(PageListConfig pageListConfig)throws Exception;
}
