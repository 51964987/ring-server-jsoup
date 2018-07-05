package ring.server.jsoup.mvc.model.page;

public class PageListCounts implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fid;
	private String enName;
	private String modelName;
	private String counts;
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	
	
}
