package ring.server.jsoup.mvc.model.page;

public class PageDetail implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String content;
	private String images;
	private String magnet;
	private String source;
	private String url;
	private String sort;
	
	private String fid;		//当前模块编号，用于展示，不保存数据
	private String curpage;	//当前页，用于展示，不保存数据
	private String code;	//状态代码，用于展示，不保存数据
	private String message;	//代码信息，用于展示，不保存数据
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getMagnet() {
		return magnet;
	}
	public void setMagnet(String magnet) {
		this.magnet = magnet;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getCurpage() {
		return curpage;
	}
	public void setCurpage(String curpage) {
		this.curpage = curpage;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
