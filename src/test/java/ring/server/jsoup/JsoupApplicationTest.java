package ring.server.jsoup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ring.server.jsoup.common.page.common.CommonDetailPage;
import ring.server.jsoup.common.page.common.CommonListPage;
import ring.server.jsoup.common.page.common.CommonPagination;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.service.page.PageConfigServiceImpl;
import ring.server.jsoup.mvc.service.page.PageDetailServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JsoupApplication.class)
public class JsoupApplicationTest {
	
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	@Autowired
	PageConfigServiceImpl pageConfigServiceImpl;
	

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
		
    	pageConfigServiceImpl.add(pageConfig);
		
//		PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
//		System.out.println(pageConfig.getCnName());
	}
	
	@Test
	public void detailPageTest(){
		
		String url = "https://cl.uozvy.com/htm_data/7/1805/3139775.html";
		try {
			PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
			new CommonDetailPage(url,pageConfig,pageDetailServiceImpl).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void listPageTest(){
		
		String url = "https://cl.uozvy.com/thread0806.php?fid=16&search=&page=1";
		try {
			PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
			new CommonListPage(url, pageConfig, pageDetailServiceImpl).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void paginationTest(){
		
		String url = "https://cl.uozvy.com/thread0806.php?fid=16&search=&page=1";
		try {
			new CommonPagination(url,pageConfigServiceImpl,pageDetailServiceImpl).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
