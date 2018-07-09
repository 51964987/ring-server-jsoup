package ring.server.jsoup.mvc.controller.detail;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.service.page.impl.PageDetailServiceImpl;
import ring.server.jsoup.mvc.service.page.impl.PageListServiceImpl;
import ring.server.jsoup.mvc.utils.DataTableResultHelper;

@RestController
@RequestMapping("detail")
public class PageDetailController {
	
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	@Autowired
	PageListServiceImpl  pageListServiceImpl ;

	@RequestMapping(value="imgs/{source}/{id}",method=RequestMethod.GET)
	public ModelAndView imgs(@PathVariable("source") String source,@PathVariable("id") String id) throws Exception{
		ModelAndView model = new ModelAndView("detail/detail-imgs");
		PageDetail pageDetail = pageDetailServiceImpl.findById(source, id);
		if(pageDetail == null){
			pageDetail = pageListServiceImpl.findDetail(source, id);
		}
		model.addObject("detail",pageDetail);
		String imgs = pageDetail.getImages();
		if(imgs!=null&&imgs.length()>0){
			model.addObject("imgs",imgs.split("\t\n"));
		}
	
		return model;
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("detail/detail-list");
	}
	
	@ResponseBody
	@RequestMapping(value="list",method=RequestMethod.POST)
	public Object findList(
			@RequestParam(required=false)Integer sEcho,
			@RequestParam(required=false)Integer iDisplayStart,
			@RequestParam(required=false)Integer iDisplayLength,
			@RequestParam(required=false)String server,
			@RequestParam(required=false)String modelUrl,
			@RequestParam(required=false)String source,
			PageDetail pageDetail
			) throws Exception{
		Map<String, Object> map = null;
		PageHelper.offsetPage(iDisplayStart, iDisplayLength, true);
		List<PageDetail> list = pageDetailServiceImpl.findList(pageDetail);
		PageInfo<PageDetail> pageInfo = new PageInfo<>(list);
		map = DataTableResultHelper.dataTableResult(sEcho+1, pageInfo);
				
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
