package ring.server.jsoup.mvc.controller.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageIndex;
import ring.server.jsoup.mvc.model.page.PageUrl;
import ring.server.jsoup.mvc.service.page.impl.PageIndexServiceImpl;
import ring.server.jsoup.mvc.service.page.impl.PageUrlServiceImpl;

@Controller
@RequestMapping("config")
public class ConfigController {
	
	@Autowired
	PageIndexServiceImpl pageIndexServiceImpl;
	@Autowired
	PageUrlServiceImpl pageUrlServiceImpl;
	
	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("config/index");
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("config/index-oper");
	}
	
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(PageIndex pageIndex,String[] domain)throws RestException{
		try {
			pageIndexServiceImpl.save(pageIndex);
			if(domain!=null&&domain.length>0){
				
				List<PageUrl> list = new ArrayList<>();
				PageUrl pageUrl = null;
				for(String url : domain){
					pageUrl = new PageUrl();
					pageUrl.setEnName(pageIndex.getEnName());
					pageUrl.setUrl(url);
					list.add(pageUrl);
				}
				
				pageUrlServiceImpl.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value="index/delete/{id}",method=RequestMethod.POST)
	public Object delete(@PathVariable("id") String id) throws RestException{
		return new ResponseEntity<>(pageIndexServiceImpl.delete(id), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="index/save",method=RequestMethod.POST)
	public Object save(PageIndex pageIndex,String[] domain,String oper) throws RestException{
		int result = 0;
		if("add".equals(oper)){			
			result = pageIndexServiceImpl.save(pageIndex);
		}else if("edit".equals(oper)){
			result = pageIndexServiceImpl.edit(pageIndex);
		}
		if(domain!=null&&domain.length>0){
			
			List<PageUrl> list = new ArrayList<>();
			PageUrl pageUrl = null;
			for(String url : domain){
				pageUrl = new PageUrl();
				pageUrl.setEnName(pageIndex.getEnName());
				pageUrl.setUrl(url);
				list.add(pageUrl);
			}
			
			pageUrlServiceImpl.add(list);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping("list")
	public ModelAndView list(){
		return new ModelAndView("config/list");
	}
	
	@RequestMapping("detail")
	public ModelAndView detail(){
		return new ModelAndView("config/detail");
	}
}
