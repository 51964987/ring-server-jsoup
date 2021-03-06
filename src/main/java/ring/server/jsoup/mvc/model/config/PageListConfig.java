package ring.server.jsoup.mvc.model.config;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ring.server.jsoup.mvc.jsonSerializer.common.CommonOperSerializer;

public class PageListConfig implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;			//英文名称
	private boolean isDownload;		//是否下载，包括图片、magnet等
	private String localpath;		//本地下载目录	
	private String lastPageGet;		//通过ID获取最后一页对象
	private String lastPageAttr;	//例如href，通过ATTR获取href
	private String lastPagePattern;		//例如"page\\=(\\d+)
	private String listUrlPattern;	//例如(https?://[[\\d\\w]+\\.]+/)thread0806.php\\?fid=(\\d+)&search=&page=(\\d+)
	private String detailUrlPattern;//例如(htm_data/\\d+/\\d+/\\d+).html
	private String imageAttr;		//例如data-src，通过ATTR获取SRC
	private String imageGet;		//例如data-src，通过Attr获取所有IMAGE对象
	private String magnetGet;		//例如a,通过标签a获取所有MAGNET对象
	private String index;			//首页地址
	private String torrentGet;		//例如a,通过标签a获取种子对象
	private String torrentPattern;		//例如以http://www.viidii.info开头

	private String cnName;			//中文名称
	@JsonSerialize(using=CommonOperSerializer.class)
	private String oper;		//前端页面操作列
	
	public boolean isDownload() {
		return isDownload;
	}
	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocalpath() {
		return localpath;
	}
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}
	public String getLastPageGet() {
		return lastPageGet;
	}
	public void setLastPageGet(String lastPageGet) {
		this.lastPageGet = lastPageGet;
	}
	public String getLastPageAttr() {
		return lastPageAttr;
	}
	public void setLastPageAttr(String lastPageAttr) {
		this.lastPageAttr = lastPageAttr;
	}
	public String getLastPagePattern() {
		return lastPagePattern;
	}
	public void setLastPagePattern(String lastPagePattern) {
		this.lastPagePattern = lastPagePattern;
	}
	public String getListUrlPattern() {
		return listUrlPattern;
	}
	public void setListUrlPattern(String listUrlPattern) {
		this.listUrlPattern = listUrlPattern;
	}
	public String getDetailUrlPattern() {
		return detailUrlPattern;
	}
	public void setDetailUrlPattern(String detailUrlPattern) {
		this.detailUrlPattern = detailUrlPattern;
	}
	public String getImageAttr() {
		return imageAttr;
	}
	public void setImageAttr(String imageAttr) {
		this.imageAttr = imageAttr;
	}
	public String getImageGet() {
		return imageGet;
	}
	public void setImageGet(String imageGet) {
		this.imageGet = imageGet;
	}
	public String getMagnetGet() {
		return magnetGet;
	}
	public void setMagnetGet(String magnetGet) {
		this.magnetGet = magnetGet;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getTorrentGet() {
		return torrentGet;
	}
	public void setTorrentGet(String torrentGet) {
		this.torrentGet = torrentGet;
	}
	public String getTorrentPattern() {
		return torrentPattern;
	}
	public void setTorrentPattern(String torrentPattern) {
		this.torrentPattern = torrentPattern;
	}
	public String getOper() {
		return this.id;
	}
	
}
