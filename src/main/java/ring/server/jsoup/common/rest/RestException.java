package ring.server.jsoup.common.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DuplicateKeyException;

/**
 * 自定义统一异常 
 * @author ring
 * @date 2017年12月4日 下午4:13:13
 * @version V1.0
 */
public class RestException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private RestCode resultCode;
	private Exception exception;
	@SuppressWarnings("unused")
	private String message;

	public RestException(RestCode resultCode,Exception exception){
		super(resultCode.getMsg());
		this.resultCode = resultCode;
		this.exception = exception;
	}
	
	public RestCode getResultCode() {
		return resultCode;
	}
	public void setResultCode(RestCode resultCode) {
		this.resultCode = resultCode;
	}
	public Exception getException() {
		return exception;
	}

	public String getMessage() {
    	//主键信息重复
    	if(this.exception instanceof DuplicateKeyException){
    		Pattern p = Pattern.compile("Duplicate entry '(.+)' for key 'PRIMARY'");
    		Matcher m = p.matcher(((DuplicateKeyException)this.exception).getMessage());
    		if(m.find()){
    			return String.format("错误代码："+RestCode.PRIMARY_KEY_EXIST_ERROR.getCode()+"，"+
    					RestCode.PRIMARY_KEY_EXIST_ERROR.getMsg(),m.group(1));
    		}
		}
    	return String.format("错误代码："+this.getResultCode().getCode()+"，"+this.getResultCode().getMsg());
	}
	
}
