package ring.server.jsoup.mvc.controller.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("config")
public class ConfigController {
	
	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("config/index");
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
