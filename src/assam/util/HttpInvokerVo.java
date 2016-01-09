package assam.util;

import java.util.Map;

public class HttpInvokerVo {

	/**
	 * 傳輸協定
	 * OPTIONS、HEAD、GET、POST、PUT、DELETE、TRACE
	 */
	private String method = null;
	
	/**
	 * api url
	 */
	private String requestUrl = null;
	
	/**
	 * request object
	 */
	private String body = null;
	private Map<String, String> parameter;
	
	/**
	 * Content-Type
	 */
	private String contentType = "text/html";
	
	/**
	 * timeout時間
	 */
	private int timeout = 30;
	
	private String charsetName = "UTF8";
	
	private String accept = "text/html";
	
	private String userAgent; // = "Java/1.7.0_17";
	
	private String connection = "keep-alive";
	
	private boolean doOutput = true;
	private boolean doInput = true;
	private boolean useCaches = false;
	
	public HttpInvokerVo() {
		String javaVersion = System.getProperty("java.version");
		this.userAgent = "Java/" + javaVersion;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public boolean isDoOutput() {
		return doOutput;
	}

	public void setDoOutput(boolean doOutput) {
		this.doOutput = doOutput;
	}

	public boolean isDoInput() {
		return doInput;
	}

	public void setDoInput(boolean doInput) {
		this.doInput = doInput;
	}

	public boolean isUseCaches() {
		return useCaches;
	}

	public void setUseCaches(boolean useCaches) {
		this.useCaches = useCaches;
	}

	public Map<String, String> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, String> parameter) {
		this.parameter = parameter;
	}

}
