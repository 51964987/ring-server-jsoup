package ring.server.jsoup.mvc.controller.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.page.PageIndex;
import ring.server.jsoup.mvc.model.page.PageUrl;
import ring.server.jsoup.mvc.service.page.impl.PageIndexServiceImpl;
import ring.server.jsoup.mvc.service.page.impl.PageUrlServiceImpl;
import ring.server.jsoup.mvc.utils.DataTableResultHelper;

@Controller
@RequestMapping("config/source")
public class DataSourceConfigController {
	
	@Autowired
	PageIndexServiceImpl pageIndexServiceImpl;
	@Autowired
	PageUrlServiceImpl pageUrlServiceImpl;
	
	@RequestMapping(value="edit/{enName}",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("enName")String enName) throws RestException{
		ModelAndView model = new ModelAndView("config/config-source-oper");
		PageIndex pageIndex = pageIndexServiceImpl.findByEnName(enName);
		model.addObject("DATA", pageIndex);
		if(!StringUtils.isEmpty(pageIndex.getDomain())){			
			model.addObject("domains", pageIndex.getDomain().split(","));
		}
		model.addObject("url", "/config/source/edit");
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
		ModelAndView model = new ModelAndView("config/config-source-oper");
		model.addObject("url", "/config/source/add");
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

	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("config/config-source");
	}
}
