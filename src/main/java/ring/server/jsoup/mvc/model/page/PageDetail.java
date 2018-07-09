package ring.server.jsoup.mvc.model.page;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ring.server.jsoup.mvc.jsonSerializer.detail.PageDetailOperSerializer;
import ring.server.jsoup.mvc.jsonSerializer.detail.PageDetailTitleSerializer;

public class PageDetail implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	@JsonSerialize(using=PageDetailTitleSerializer.class)
	private String title;
	private String content;
	private String images;
	private String magnet;
	private String torrent;
	private String source;
	private String url;
	private String sort;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date ts;
	@JsonSerialize(using=PageDetailOperSerializer.class)
	private String oper;
	private String fid;		//当前模块编号，用于展示，不保存数据
	private String curpage;	//当前页，用于展示，不保存数据
	private String code;	//状态代码，用于展示，不保存数据
	private String message;	//代码信息，用于展示，不保存数据
	
	private String author;	//LEFT JOIN page_list
	private String createDate;	//LEFT JOIN page_list
	private String clickNum;	//LEFT JOIN page_list
	private String modelName;	//LEFT JOIN page_model
	
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
	public String getTorrent() {
		return torrent;
	}
	public void setTorrent(String torrent) {
		this.torrent = torrent;
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
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public String getOper() {
		return this.id;
	}
}
