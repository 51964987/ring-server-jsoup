package ring.server.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import ring.server.jsoup.common.util.HttpUrlUtil;

public class DocumentTest {

	@Test
	public void DocTest(){
		String url = "https://cl.wy8.info/htm_data/7/1805/3141479.html";
		url = "https://cl.wy8.info/htm_data/20/1302/859210.html";
		try {
			Document doc = HttpUrlUtil.get(url);
			Elements ths = doc.getElementsByTag("th");
			if(ths!=null&&ths.size()>0){
				for(int i=0;i<ths.size();i++){
					Elements content = ths.get(i).getElementsByClass("tpc_content");
					if(content!=null){
						System.out.println(content.outerHtml());
					}
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
