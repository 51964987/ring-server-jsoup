package ring.server.jsoup.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import ring.server.jsoup.common.callable.DownloadImageCallable;

public class DownloadUtil {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//最大并发线程数
	int threadNum = 5;
	
	/**
	 * 磁力连接字符串,回车符隔开
	 * @param as
	 * @param outputFilePath
	 * @param isDownload
	 * @throws Exception 
	 * @author ring
	 * @date 2018年6月3日 下午11:46:21
	 * @version V1.0
	 */
	public String magnetText(Elements as,String outputFilePath,boolean isDownload) throws Exception{
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<as.size();i++){
			
			//创建目录
			File file = new File(outputFilePath).getParentFile();
			if(!file.exists()){
				file.mkdirs();
			}
			
			Elements item = as.get(i).getElementsMatchingText(Pattern.compile("magnet.*"));
			if(item!=null&&item.size()>0){
				if(sb.length()>0){
					sb.append("\t\n");
				}
				sb.append(item.get(0).text());
			}
		}
		if(isDownload){
			downloadMagnet(sb.toString(), outputFilePath);
		}
		return sb.toString();
	}
	
	/**
	 * 下载Magnet.txt
	 * @param magentText
	 * @param outputFilePath 
	 * @author ring
	 * @date 2018年6月11日 上午7:59:12
	 * @version V1.0
	 */
	public void downloadMagnet(String magentText,String outputFilePath){
		FileOutputStream fos = null; 
		OutputStreamWriter osw = null; 
		try {
			if(!StringUtils.isEmpty(magentText)){				
				fos = new FileOutputStream(outputFilePath, false);  
				osw = new OutputStreamWriter(fos, "UTF-8");  
				osw.write(magentText);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(osw!=null){
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}
		
	/**
	 * 图片字符串,回车符隔开
	 * @param imgs
	 * @param attrSrc
	 * @param outputpath
	 * @param isDownload
	 * @throws Exception 
	 * @author ring
	 * @date 2018年6月3日 下午9:03:47
	 * @version V1.0
	 */
	public String imagesText(Elements imgs,String attrSrc,String outputpath,boolean isDownload) throws Exception{
		StringBuffer imagesSb = new StringBuffer();
		if(imgs != null && imgs.size()>0){			
			for(int i=0;i<imgs.size();i++){
				String src = imgs.get(i).absUrl(attrSrc);
				if(imagesSb.length()>0){
					imagesSb.append("\t\n");
				}
				imagesSb.append(src);
			}
			
			if(isDownload){				
				downloadImage(imagesSb.toString(), attrSrc, outputpath);
			}
		}		
		return imagesSb.toString();
	}
	
	/**
	 * 下载图片
	 * @param imgs
	 * @param attrSrc
	 * @param outputpath
	 * @throws Exception 
	 * @author ring
	 * @date 2018年6月3日 下午9:03:47
	 * @version V1.0
	 */
	public void downloadImage(String imagesText,String attrSrc,String outputpath) throws Exception{
		if(imagesText != null && imagesText.length()>0){			
			String[] imgs = imagesText.split("\t\n");
			// 开启线程池  
			ExecutorService pool = Executors.newFixedThreadPool(imgs.length>threadNum?threadNum:imgs.length); 
			List<Future<JSONObject>> list = new ArrayList<Future<JSONObject>>();  
			for(int i=0;i<imgs.length;i++){
				Future<JSONObject> f = pool.submit(new DownloadImageCallable(i+1,imgs[i], outputpath));  
				list.add(f);  
			}
			
			// 关闭线程池  
			pool.shutdown();  
			
			// 获取所有并发任务的运行结果  
			for (Future<JSONObject> f : list) {  
				// 从Future对象上获取任务的返回值，并输出到控制台  
				JSONObject result = f.get();
				if(result!=null&&"-1".equals(result.getString("code"))){
					logger.error(result.toJSONString());
				}
			}  
		}		
	}
	
}
