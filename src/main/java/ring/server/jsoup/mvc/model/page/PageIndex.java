package ring.server.jsoup.mvc.model.page;

public class PageIndex implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String enName;
	private String domain;
	private String cnName;
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
}
