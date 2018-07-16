package ring.server.jsoup.mvc.controller.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("config/detail")
public class DataSourceDetailConfigController {
	
	@RequestMapping("index")
	public ModelAndView detail(){
		return new ModelAndView("config/config-detail");
	}
}
