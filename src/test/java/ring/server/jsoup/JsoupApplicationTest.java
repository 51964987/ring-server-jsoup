package ring.server.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ring.server.jsoup.common.page.common.CommonDetailPage;
import ring.server.jsoup.common.page.common.CommonIndex;
import ring.server.jsoup.common.page.common.CommonListPage;
import ring.server.jsoup.common.page.common.CommonPagination;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.service.page.impl.PageConfigServiceImpl;
import ring.server.jsoup.mvc.service.page.impl.PageDetailServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JsoupApplication.class)
public class JsoupApplicationTest {
	
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	@Autowired
	PageConfigServiceImpl pageConfigServiceImpl;
	@Autowired
	PageListMapper pageListMapper;
	

	@Test
	public void pageConfigTest(){
		PageConfig pageConfig = new PageConfig();
    	pageConfig.setDownload(false);
    	pageConfig.setLocalpath("F:/t66y");
    	pageConfig.setCnName("XXX");
    	pageConfig.setEnName("T66Y");
    	pageConfig.setListUrlPattern("(https?://[[\\d\\w]+\\.]+/)thread0806.php\\?fid=(\\d+)&search=&page=(\\d+)");
    	pageConfig.setDetailUrlPattern("(htm_data/\\d+/\\d+/)(\\d+).html");
    	pageConfig.setImageAttr("data-src");
    	pageConfig.setImageGet("data-src");
    	pageConfig.setMagnetGet("a");
    	pageConfig.setLastPageGet("last");
		pageConfig.setLastPageAttr("href");
		pageConfig.setLastPagePattern("page\\=(\\d+)");
		pageConfig.setIndex("");
		
    	try {
			pageConfigServiceImpl.add(pageConfig);
		} catch (RestException e) {
			e.printStackTrace();
		}
		
//		PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
//		System.out.println(pageConfig.getCnName());
	}
	
	@Test
	public void detailPageTest(){
		
		String url = "https://cl.wy8.info/htm_data/16/1807/3193734.html";
		try {
			PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
			new CommonDetailPage(url,pageConfig,pageDetailServiceImpl).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void listPageTest(){
		
		String url = "https://cl.wy8.info/thread0806.php?fid=7&search=&page=13";
		try {
			PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
			List<PageList> list = new ArrayList<>();
			new CommonListPage(url,pageConfig,list).call();
			if(list.size()>0){
				System.out.println("批量插入或更新正式表");
				pageListMapper.addList(list);
			}else{
				System.err.println("没有数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void paginationTest(){
		
		String url = "https://cl.wy8.info/thread0806.php?fid=7&search=&page=1";
		try {
			PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
			new CommonPagination(url,pageConfig,pageListMapper).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void CommonIndexTest(){
		
		String url = "https://cl.wy8.info/index.php";
		try {
			PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
			new CommonIndex(url,pageConfig,pageListMapper).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
