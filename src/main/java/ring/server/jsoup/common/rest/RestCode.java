package ring.server.jsoup.common.rest;

public enum RestCode {
	
	/** 后台数据库出错 */
	DATATABLE_ERROR("600","后台数据库出错！"),
	/** 请求超时 */
	TIMEOUT_ERROR("601","请求超时！"),
	/** 存在重复主键数据 */
	PRIMARY_KEY_EXIST_ERROR("602","存在主键数据：%s。"),
	
	/** 成功 */
	SUCCESS("200",""),
	/** 后台服务器发生错误。请稍候再试！ */
	SERVER_ERROR("500","后台服务器发生错误。请稍候再试！");
	
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
