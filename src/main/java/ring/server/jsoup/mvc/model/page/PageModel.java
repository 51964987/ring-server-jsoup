package ring.server.jsoup.mvc.model.page;

public class PageModel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String enName;
	private String model;
	private String modelName;
	private String modelUrl;
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelUrl() {
		return modelUrl;
	}
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	
	
}
