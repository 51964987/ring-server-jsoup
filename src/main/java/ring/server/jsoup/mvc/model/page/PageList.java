package ring.server.jsoup.mvc.model.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ring.server.jsoup.mvc.jsonSerializer.page.PageListOperSerializer;
import ring.server.jsoup.mvc.jsonSerializer.page.PageListTitleSerializer;

public class PageList implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	@JsonSerialize(using=PageListTitleSerializer.class)
	private String title;
	private String author;
	private String createDate;
	private String clickNum;
	private String url;
	private String fid;
	private String yearMonth;
	private String target;
	private String ts;
	private String source;
	@JsonSerialize(using=PageListOperSerializer.class)
	private String oper;
	private String modelName;
	
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getClickNum() {
		return clickNum;
	}
	public void setClickNum(String clickNum) {
		this.clickNum = clickNum;
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
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOper() {
		return this.id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
}
