package ring.server.jsoup.mvc.controller.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.model.config.PageListConfig;
import ring.server.jsoup.mvc.service.config.impl.PageListConfigServiceImpl;
import ring.server.jsoup.mvc.utils.DataTableResultHelper;

@Controller
@RequestMapping("config/list")
public class DataSourceListConfigController {

	@Autowired
	PageListConfigServiceImpl pageListConfigServiceImpl;
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id")String id) throws RestException{
		ModelAndView model = new ModelAndView("config/config-list-oper");
		model.addObject("DATA", pageListConfigServiceImpl.findById(id));
		model.addObject("url", "/config/source/edit");
		return model;
	}
	
	@ResponseBody
	@Transactional(rollbackFor=RestException.class)
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public Object edit(PageListConfig pageListConfig)throws RestException{
		return new ResponseEntity<>(pageListConfigServiceImpl.edit(pageListConfig), HttpStatus.OK);
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView model = new ModelAndView("config/config-list-oper");
		model.addObject("url", "/config/source/add");
		return model;
	}

	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(PageListConfig pageListConfig)throws RestException{
		return new ResponseEntity<>(pageListConfigServiceImpl.save(pageListConfig), HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	public Object delete(@PathVariable("id") String id) throws RestException{
		return new ResponseEntity<>(pageListConfigServiceImpl.delete(id), HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value="list",method=RequestMethod.POST)
	public Object findList(
			@RequestParam(required=false)Integer sEcho,
			@RequestParam(required=false)Integer iDisplayStart,
			@RequestParam(required=false)Integer iDisplayLength,
			PageListConfig pageListConfig
			)throws RestException{
		
		Map<String, Object> map = null;
		PageHelper.offsetPage(iDisplayStart, iDisplayLength, true);
		List<PageListConfig> list = pageListConfigServiceImpl.findList(pageListConfig);
		PageInfo<PageListConfig> pageInfo = new PageInfo<>(list);
		map = DataTableResultHelper.dataTableResult(sEcho+1, pageInfo);
				
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping("index")
	public ModelAndView list(){
		return new ModelAndView("config/config-list");
	}
}
