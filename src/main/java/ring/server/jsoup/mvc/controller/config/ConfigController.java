package ring.server.jsoup.mvc.controller.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
	
	@RequestMapping(value="edit/{enName}",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("enName")String enName) throws RestException{
		ModelAndView model = new ModelAndView("config/index-oper");
		PageIndex pageIndex = pageIndexServiceImpl.findByEnName(enName);
		model.addObject("DATA", pageIndex);
		if(!StringUtils.isEmpty(pageIndex.getDomain())){			
			model.addObject("domains", pageIndex.getDomain().split(","));
		}
		model.addObject("url", "/config/edit");
		return model;
	}
	
	@ResponseBody
	@Transactional(rollbackFor=RestException.class)
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public Object edit(PageIndex pageIndex,String[] domain)throws RestException{
		pageIndexServiceImpl.edit(pageIndex);
		if(domain!=null&&domain.length>0){
			pageUrlServiceImpl.deleteByEnName(pageIndex.getEnName());
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
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView model = new ModelAndView("config/index-oper");
		model.addObject("url", "/config/add");
		return model;
	}

	@Transactional(rollbackFor=RestException.class)
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(PageIndex pageIndex,String[] domain)throws RestException{
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
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	public Object delete(@PathVariable("id") String id) throws RestException{
		return new ResponseEntity<>(pageIndexServiceImpl.delete(id), HttpStatus.OK);
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
