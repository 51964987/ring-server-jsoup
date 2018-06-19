package ring.server.jsoup.mvc.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ring.server.jsoup.common.page.common.CommonPagination;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.service.page.PageConfigServiceImpl;
import ring.server.jsoup.mvc.service.page.PageDetailServiceImpl;

@RestController
@RequestMapping("page")
public class PageController {
	
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	@Autowired
	PageConfigServiceImpl pageConfigServiceImpl;
	@Autowired
	PageListMapper pageListMapper;
	
	@RequestMapping("exec")
	public ResponseEntity<Object> exec(String url){
		try {
			new CommonPagination(url,pageConfigServiceImpl,pageListMapper).call();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
