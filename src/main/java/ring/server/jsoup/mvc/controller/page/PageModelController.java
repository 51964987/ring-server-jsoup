package ring.server.jsoup.mvc.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.service.page.impl.PageModelServiceImpl;

@RestController
@RequestMapping("model")
public class PageModelController {
	
	@Autowired
	PageModelServiceImpl pageModelServiceImpl;

	@RequestMapping("change")
	public ResponseEntity<Object> counts(String source)throws RestException{
		try {
			return new ResponseEntity<>(pageModelServiceImpl.findBySource(source),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
