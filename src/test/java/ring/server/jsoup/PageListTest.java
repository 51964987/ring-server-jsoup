package ring.server.jsoup;

import java.net.SocketTimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class PageListTest {
	
	@Test
	public void CommonIndexTest(){
		
		String url = "https://cl.wy8.info/index.php";
		try {
			Pattern p = Pattern.compile("https?\\://[\\w+\\.]+/");
			Matcher m = p.matcher(url);
			if(m.find()){
				System.out.println(m.group());
			}
			//new CommonIndex(url,null,null).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void pagelistTest(){
		try {
			String url = "https://cl.wy8.info/thread0806.php?fid=7&search=&page=13";
			
			Document doc = null;
			boolean timeout = false;
			int readNum=0;
			do{				
				try {
					doc = Jsoup.connect(url ).get();
					timeout = false;
					if(readNum==10){
						break;
					}
				} catch (SocketTimeoutException e) {
					e.printStackTrace();
					timeout = true;
					readNum++;
				}
			}while(timeout);
			
			if(timeout){
				throw new Exception("请求超时="+url);
			}
			
			Element table = doc.getElementById("ajaxtable");
			Elements trs = table.getElementsByClass("tr3 t_one tac");
			for(int i=0;i<trs.size();i++){
				Elements tds = trs.get(i).children();
				Element item = tds.get(1).getElementsByTag("h3").get(0).child(0);
				System.out.println("title="+item.text());
				System.out.println("url="+item.attr("href"));
				Element author = tds.get(2).getElementsByTag("a").get(0);
				System.out.println("author="+author.text());
				System.out.println("createDate="+author.nextElementSibling().text());
				System.out.println("clickNum="+tds.get(3).text());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
