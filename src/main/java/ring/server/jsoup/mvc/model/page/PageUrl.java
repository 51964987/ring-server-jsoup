package ring.server.jsoup.mvc.model.page;

public class PageUrl implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String configId;
	private String url;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
