package ring.server.jsoup.common.rest;

public enum RestCode {
	
	/** 后台数据库出错 */
	DATATABLE_ERROR("600","后台数据库出错！"),
	/** 请求超时 */
	TIMEOUT_ERROR("601","请求超时！"),
	
	/** 成功 */
	SUCCESS("200",""),
	/** 后台出错 */
	SERVER_ERROR("500","后台出错");
	
	private String code;
	private String msg;
	
	RestCode(String code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
