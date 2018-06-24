package ring.server.jsoup.mvc.controller.page;

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

import ring.server.jsoup.common.page.common.CommonDetailPage;
import ring.server.jsoup.common.page.common.CommonPagination;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.page.PageConfig;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.service.page.PageConfigServiceImpl;
import ring.server.jsoup.mvc.service.page.PageDetailServiceImpl;
import ring.server.jsoup.mvc.service.page.PageListService;
import ring.server.jsoup.mvc.utils.DataTableResultHelper;

@RestController
@RequestMapping("page")
public class PageController {
	
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	@Autowired
	PageConfigServiceImpl pageConfigServiceImpl;
	
	@Autowired
	PageListService pageListService;
	@Autowired
	PageListMapper pageListMapper;
	

	@RequestMapping(value="detail/{id}",method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable("id") String id) throws Exception{
		ModelAndView model = new ModelAndView("page/page-detail");
		PageList pageList = pageListService.findById(id);
		PageConfig pageConfig = pageConfigServiceImpl.get("T66Y");
		//从page_url获取URL
		//...
		String url = "https://cl.wy8.info/"+pageList.getUrl();
		model.addObject("url",url);
		
		PageDetail pageDetail = new CommonDetailPage(url, pageConfig, pageDetailServiceImpl).call();
		model.addObject("detail", pageDetail);
		
		String imgs = pageDetail.getImages();
		if(imgs!=null&&imgs.length()>0){
			model.addObject("imgs",imgs.split("\t\n"));
		}
		
		String mags = pageDetail.getMagnet();
		if(mags!=null&&mags.length()>0){
			model.addObject("mags",mags.split("\t\n"));
		}
		
		return model;
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("page/page-list");
	}
	
	@ResponseBody
	@RequestMapping(value="list",method=RequestMethod.POST)
	public Object findList(
			@RequestParam(required=false)Integer sEcho,
			@RequestParam(required=false)Integer iDisplayStart,
			@RequestParam(required=false)Integer iDisplayLength,
			@RequestParam(required=false)String server,
			PageList pageList
			) throws Exception{
		Map<String, Object> map = null;
		//本地服务器
		if("1".equals(server)){			
			PageHelper.offsetPage(iDisplayStart, iDisplayLength, true);
			List<PageList> list = pageListService.findList(pageList);
			PageInfo<PageList> pageInfo = new PageInfo<>(list);
			map = DataTableResultHelper.dataTableResult(sEcho+1, pageInfo);
		}else if("2".equals(server)){			
			//目标服务器
			
		}
		
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
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
