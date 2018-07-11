package ring.server.jsoup.mvc.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ring.server.jsoup.mvc.model.page.PageIndex;
import ring.server.jsoup.mvc.service.page.impl.PageIndexServiceImpl;

@Controller
@RequestMapping("config")
public class ConfigController {
	
	@Autowired
	PageIndexServiceImpl pageIndexServiceImpl;
	
	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("config/index");
	}

	@ResponseBody
	@RequestMapping(value="index/delete/{id}",method=RequestMethod.POST)
	public Object delete(@PathVariable("id") String id) throws Exception{
		return new ResponseEntity<>(pageIndexServiceImpl.delete(id), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="index/save",method=RequestMethod.POST)
	public Object save(PageIndex pageIndex,String[] domain,String oper) throws Exception{
		int result = 0;
		if("add".equals(oper)){			
			result = pageIndexServiceImpl.save(pageIndex);
		}else if("edit".equals(oper)){
			result = pageIndexServiceImpl.edit(pageIndex);
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
