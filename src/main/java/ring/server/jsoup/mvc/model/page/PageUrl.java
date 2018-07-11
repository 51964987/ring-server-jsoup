package ring.server.jsoup.mvc.model.page;

public class PageUrl implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String enName;
	private String url;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
