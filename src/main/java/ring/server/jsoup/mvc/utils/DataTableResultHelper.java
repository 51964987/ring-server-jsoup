package ring.server.jsoup.mvc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

/**
 * 用于返回dataTable插件数据
 *  
 * @author ring
 * @date 2017年12月4日 下午4:31:26
 * @version V1.0
 */
public class DataTableResultHelper {
	private static Logger logger = LoggerFactory.getLogger(DataTableResultHelper.class);
	
	public static <T> Map<String, Object> dataTableResult(int sEcho,PageInfo<T> pageInfo){
		Map<String, Object> map = new HashMap<>();
		
		map.put("sEcho", sEcho);
		map.put("iTotalRecords", (int)pageInfo.getTotal());
		map.put("iTotalDisplayRecords", (int)pageInfo.getTotal());
		map.put("aaData", pageInfo.getList());
		map.put("code", "200");
		map.put("message", "OK");
		
		if(logger.isDebugEnabled()){
			logger.debug("page dataTable result :"+JSON.toJSONString(pageInfo.getList()));
		}
		
		return map;
	}
	
	public static <T> Map<String, Object> dataTableResult(int sEcho,List<T> list,int lastPage){
		Map<String, Object> map = new HashMap<>();
		
		map.put("sEcho", sEcho);
		map.put("iTotalRecords", lastPage*100);
		map.put("iTotalDisplayRecords", list);
		map.put("aaData", list);
		map.put("code", "200");
		map.put("message", "OK");
		
		if(logger.isDebugEnabled()){
			logger.debug("page dataTable result :"+JSON.toJSONString(list));
		}
		
		return map;
	}
}
