package ring.server.jsoup.mvc.model.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ring.server.jsoup.mvc.jsonSerializer.index.PageIndexOperSerializer;

public class PageIndex implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String enName;
	private String domain;
	private String cnName;
	
	@JsonSerialize(using=PageIndexOperSerializer.class)
	private String oper;
	
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
	public String getOper() {
		return this.enName;
	}
	
}
