package ring.server.jsoup.mvc.controller.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ring.server.jsoup.common.page.common.CommonListPage;
import ring.server.jsoup.common.page.common.CommonPagination;
import ring.server.jsoup.common.rest.RestException;
import ring.server.jsoup.mvc.dao.page.PageListMapper;
import ring.server.jsoup.mvc.model.config.PageListConfig;
import ring.server.jsoup.mvc.model.page.PageDetail;
import ring.server.jsoup.mvc.model.page.PageList;
import ring.server.jsoup.mvc.model.page.PageUrl;
import ring.server.jsoup.mvc.service.config.impl.PageListConfigServiceImpl;
import ring.server.jsoup.mvc.service.page.impl.PageDetailServiceImpl;
import ring.server.jsoup.mvc.service.page.impl.PageListServiceImpl;
import ring.server.jsoup.mvc.service.page.impl.PageUrlServiceImpl;
import ring.server.jsoup.mvc.utils.DataTableResultHelper;

@RestController
@RequestMapping("page")
public class PageController {
	
	@Autowired
	PageDetailServiceImpl pageDetailServiceImpl;
	@Autowired
	PageListConfigServiceImpl pageListConfigServiceImpl;
	
	@Autowired
	PageListServiceImpl  pageListServiceImpl ;
	@Autowired
	PageUrlServiceImpl  pageUrlServiceImpl ;
	@Autowired
	PageListMapper pageListMapper;

	@RequestMapping("counts")
	public ResponseEntity<Object> counts()throws RestException{
		return new ResponseEntity<>(pageListServiceImpl.findListCounts(),HttpStatus.OK);
	}

	@RequestMapping(value="detail/{source}/{id}",method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable("source") String source,@PathVariable("id") String id) throws RestException{
		ModelAndView model = new ModelAndView("page/page-detail");
		
		PageDetail pageDetail = pageListServiceImpl.findDetail(source, id);
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
			@RequestParam(required=false)String modelUrl,
			@RequestParam(required=false)String source,
			PageList pageList
			) throws RestException{
		
		if(StringUtils.isEmpty(source)){
			return new ResponseEntity<>("数据来源为空", HttpStatus.BAD_REQUEST);
		}
		
		Map<String, Object> map = null;
		//本地服务器
		if("1".equals(server)){			
			PageHelper.offsetPage(iDisplayStart, iDisplayLength, true);
			List<PageList> list = pageListServiceImpl.findList(pageList);
			PageInfo<PageList> pageInfo = new PageInfo<>(list);
			map = DataTableResultHelper.dataTableResult(sEcho+1, pageInfo);
		}else if("2".equals(server)){	
			PageListConfig pageListConfig = pageListConfigServiceImpl.findById(source);
			//目标服务器
			String url = null;
			List<PageUrl> pageUrls = pageUrlServiceImpl.findByEnName(pageListConfig.getId());
			if(pageUrls!=null&&pageUrls.size()>0){
				//判断是否有效
				//...
				url = pageUrls.get(0).getUrl();
			}
			List<PageList> list = new ArrayList<>();
			int lastPage = (int) new CommonListPage(url+modelUrl,pageListConfig,list).call();
			map = DataTableResultHelper.dataTableResult(sEcho+1, list,lastPage);
		}
				
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping("exec")
	public ResponseEntity<Object> exec(
			@RequestParam(required=false)String server,
			@RequestParam(required=false)String modelUrl,
			@RequestParam(required=false)String source)throws RestException{
		PageListConfig pageListConfig = pageListConfigServiceImpl.findById(source);
		String url = null;
		List<PageUrl> pageUrls = pageUrlServiceImpl.findByEnName(pageListConfig.getId());
		if(pageUrls!=null&&pageUrls.size()>0){
			//判断是否有效
			//...
			url = pageUrls.get(0).getUrl();
		}
		new CommonPagination(url+modelUrl,pageListConfig,pageListMapper).call();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
