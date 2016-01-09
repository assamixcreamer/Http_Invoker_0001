package assam.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import assam.util.HttpInvokerUtil;
import assam.util.HttpInvokerVo;

public class HttpTest {

	@Test
	public void test() throws Exception{
		//可移至外部設定
		String ip_port = "54.64.237.112:80";
		
		//api url
		String apiUrl = "http://" + ip_port + "/api/rest/notifications/push_message";
		
		//命名規則暫時先這樣
		Map<String, Object> jsonOb1_0 = new HashMap<>();
		Map<String, Object> jsonOb1_1 = new HashMap<>();
		jsonOb1_1.put("beacon_uuid", "16888-55");
		jsonOb1_1.put("key", "955e1e63a0b61361005d8df6d928e86ea5b52630");
		jsonOb1_0.put("args", jsonOb1_1);
		
		HttpInvokerUtil httpTool = new HttpInvokerUtil();
		HttpInvokerVo connectInfo = httpTool.getHttpInvokerVo();
		
		connectInfo.setRequestUrl(apiUrl);
		connectInfo.setMethod("POST");
		connectInfo.setContentType("application/json");
		
		String responseJson = httpTool.invokeRtnString(jsonOb1_0);
		
		System.out.println(responseJson);
	}
}
