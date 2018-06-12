package ring.server.jsoup.common.callable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class DownloadImageCallable implements Callable<JSONObject> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private int threadIndex;
	private String dataSrc;
	private String outputpath;
	
	public DownloadImageCallable(int threadIndex, String dataSrc, String outputpath) {
		super();
		this.threadIndex = threadIndex;
		this.dataSrc = dataSrc;
		this.outputpath = outputpath;
	}

	@Override
	public JSONObject call() throws Exception {
		
		//返回对象
		JSONObject result = new JSONObject();
		result.put("dataSrc", dataSrc);
		result.put("outputpath", outputpath);
		
		//当前页面
		String prefix = dataSrc.substring(dataSrc.lastIndexOf("/"), dataSrc.lastIndexOf("."));
		
		//线程名称
		Thread.currentThread().setName(prefix+"-IMAGE-"+threadIndex);
		if(!StringUtils.isEmpty(dataSrc)){
			try {
				download(threadIndex,dataSrc,outputpath);
				result.put("code", "200");
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
				result.put("code", "-1");
				result.put("msg", e.getMessage());
			}
		}
		return result;
	}  

	public void download(int imageIndex,String url,String outputPath) throws FileNotFoundException, IOException{
		File file = new File(outputPath);
		if(!file.exists()){
			file.mkdirs();
		}		
		logger.info(url);
		//jsoup有带个方法设置大小maxBodySize(3000000)，可根据自己情况设置
		BufferedInputStream bufferedInputStream = Jsoup.connect(url).ignoreContentType(true).
				maxBodySize(3000000).ignoreHttpErrors(true).execute().bodyStream();
		String filename = url.substring(url.lastIndexOf("/")+1, url.length());
		//jsoup下载图片有两种方式，bodyAsBytes()，bodyStream()，文件比较大时选用后者，因为有带缓存。文件读写直接用的commons-io工具类
		IOUtils.copyLarge(bufferedInputStream,new FileOutputStream(outputPath+"/"+imageIndex+"_"+filename));		
	}
}
