package cn.dblearn.dbblog.common.exception;

import cn.dblearn.dbblog.common.warpper.HttpResponseCode;

/**
 * http 请求异常
 * @author developer001
 */
public class HttpRequestException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private HttpResponseCode httpCode;

	public HttpRequestException(int httpCode,String message)
    {
		super(message);
		this.httpCode = HttpResponseCode.getByValue(httpCode);
    }

    public HttpRequestException(int httpCode,Throwable throwable)
    {
    	super(throwable);
    	this.httpCode = HttpResponseCode.getByValue(httpCode);
    }

    public HttpRequestException(int httpCode,String message, Throwable throwable)
    {
    	super(message, throwable);
    	this.httpCode = HttpResponseCode.getByValue(httpCode);
    }

	public HttpResponseCode getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(HttpResponseCode httpCode) {
		this.httpCode = httpCode;
	}

}
