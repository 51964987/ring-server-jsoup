package ring.server.jsoup.mvc.model.page;

public class PageListCounts implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fid;
	private String source;
	private String name;
	private String value;
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
