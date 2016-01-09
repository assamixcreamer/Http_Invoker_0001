package assam.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class HttpInvokerUtil {

private final ObjectMapper jsonMapper = new ObjectMapper();
	
	private String requestJson;
	
	private String responseJson;
	
	/**
	 * 連線資訊
	 */
	private HttpInvokerVo httpInvokerVo = new HttpInvokerVo();
	
	private final HttpInvoker httpInvoker = new HttpInvoker();
	
	public String invokeRtnString(Object requestObject) throws Exception{
		
		
		try {
			this.requestJson = this.jsonMapper.writeValueAsString(requestObject);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		try {
			this.responseJson = httpPOST();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return responseJson;
	}
	
	public Object invokeRtnObject(Object requestObject, Class<?> returnType){
		Object resonseObject = null;
		
		try {
			this.requestJson = this.jsonMapper.writeValueAsString(requestObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.responseJson = httpPOST();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			resonseObject = this.jsonMapper.readValue(this.responseJson, returnType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resonseObject;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T invokeRtnGenerics(Object requestObject, T type){
		T resonseObject = null;
		
		try {
			this.requestJson = this.jsonMapper.writeValueAsString(requestObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.responseJson = httpPOST();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			resonseObject = (T) this.jsonMapper.readValue(this.responseJson, type.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resonseObject;
	}
	
	private String httpPOST() throws Exception {
		
		 //設定request參數
		httpInvokerVo.setBody(requestJson);

		return this.httpInvoker.invokerAndGetResponse(httpInvokerVo);
	}

	public HttpInvokerVo getHttpInvokerVo() {
		return httpInvokerVo;
	}

	public void setHttpInvokerVo(HttpInvokerVo httpInvokerVo) {
		this.httpInvokerVo = httpInvokerVo;
	}
}
