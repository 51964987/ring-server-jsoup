package ring.server.jsoup.mvc.controller.page;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageIndex;
import ring.server.jsoup.mvc.service.page.impl.PageIndexServiceImpl;
import ring.server.jsoup.mvc.utils.DataTableResultHelper;

@RestController
@RequestMapping("index")
public class PageIndexController {
	
	@Autowired
	PageIndexServiceImpl pageIndexServiceImpl;

	@RequestMapping("change")
	public ResponseEntity<Object> counts(String source)throws RestException{
		try {
			return new ResponseEntity<>(pageIndexServiceImpl.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="list",method=RequestMethod.POST)
	public Object findList(
			@RequestParam(required=false)Integer sEcho,
			@RequestParam(required=false)Integer iDisplayStart,
			@RequestParam(required=false)Integer iDisplayLength,
			PageIndex pageIndex
			)throws RestException{
		
		Map<String, Object> map = null;
		PageHelper.offsetPage(iDisplayStart, iDisplayLength, true);
		List<PageIndex> list = pageIndexServiceImpl.findList(pageIndex);
		PageInfo<PageIndex> pageInfo = new PageInfo<>(list);
		map = DataTableResultHelper.dataTableResult(sEcho+1, pageInfo);
				
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
