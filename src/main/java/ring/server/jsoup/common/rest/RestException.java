package ring.server.jsoup.common.rest;

/**
 * 自定义统一异常 
 * @author ring
 * @date 2017年12月4日 下午4:13:13
 * @version V1.0
 */
public class RestException extends Exception {
	private static final long serialVersionUID = 1L;
	private RestCode resultCode;
	private String errorMessage;
	
	public RestException(RestCode resultCode) {
		super();
		this.resultCode = resultCode;
	}
	public RestException(RestCode resultCode,String errorMessage){
		super(resultCode.getMsg());
		this.resultCode = resultCode;
		this.errorMessage = errorMessage;
	}
	
	public RestCode getResultCode() {
		return resultCode;
	}
	public void setResultCode(RestCode resultCode) {
		this.resultCode = resultCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
}
