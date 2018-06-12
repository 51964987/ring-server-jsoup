package ring.server.jsoup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class JsoupTest {
	@Test
	public void threadHtml(){
		try {
			String url = "https://cl.uozvy.com/thread0806.php?fid=16&search=&page=1";
			
			/*Document doc = Jsoup.connect(url).get();
			//<a href="thread0806.php?fid=16&amp;search=&amp;page=154" id="last">＞</a>
			Element lastATag = doc.getElementById("last");
			System.out.println(lastATag);
			String href = lastATag.attr("href");
			Pattern p = Pattern.compile(".*page\\=(\\d+)");
			Matcher m = p.matcher(href);
			if(m.find()){
				System.out.println("last page is "+m.group(1));
			}*/
			
			Pattern p = Pattern.compile("page\\=(\\d+)");
			Matcher m = p.matcher(url);
			 StringBuffer sb = new StringBuffer();  
			if(m.find()){
				//System.out.println(m.reset(input));
				System.out.println(m.group(1));
				 m.appendReplacement(sb, "page=2");  
				 System.out.println(sb.toString());  
			}
			System.out.println(sb.toString());  
			
//			new CaoliuListPage(url).call();
			
			//String url = "https://cl.uozvy.com/htm_data/7/1806/3163581.html";
			//url = "https://cl.uozvy.com/htm_data/7/1805/3139775.html";
			//new CaoliuDetailPage(url).call();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
