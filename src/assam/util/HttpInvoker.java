package assam.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpInvoker {

	private boolean showInfo = false;

	public HttpInvoker() {

	}

	public HttpInvoker(boolean showInfo) {
		this.showInfo = showInfo;
	}

	public String invokerAndGetResponse(HttpInvokerVo hiVo) throws Exception {
		String method = hiVo.getMethod();

		HttpURLConnection httpConn = getHttpURLConnection(hiVo);

		if (this.showInfo) {
			printInfo(hiVo);
		}

		try {
			int resCode = httpConn.getResponseCode();
			if (resCode != HttpURLConnection.HTTP_OK) {
				throw new Exception("ResponseCode: " + resCode);
			}
		} catch (IOException e) {
			printInfo(hiVo);
			throw e;
		}

		if ("DELETE".equals(method)) {
			return "";
		}

		StringBuffer resSB = new StringBuffer();
		String readLine;

		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), hiVo.getCharsetName());
		BufferedReader resReader = new BufferedReader(isr);
		while ((readLine = resReader.readLine()) != null) {
			resSB.append(readLine);
		}
		resReader.close();

		return resSB.toString();
	}
	
	private void printInfo(HttpInvokerVo hiVo) {
		String requestUrl = hiVo.getRequestUrl();
		String method = hiVo.getMethod();
		String body = hiVo.getBody();
		String contentType = hiVo.getContentType();
		String charsetName = hiVo.getCharsetName();
		String userAgent = hiVo.getUserAgent();
		String connection = hiVo.getConnection();
		String accept = hiVo.getAccept();
		
		System.out.println("<" + this.getClass().getSimpleName() + ">");
		System.out.println("RequestUrl = " + requestUrl);
		System.out.println("method = " + method);
		System.out.println("Body = " + body);
		System.out.println("ContentType = " + contentType);
		System.out.println("CharsetName = " + charsetName);
		System.out.println("UserAgent = " + userAgent);
		System.out.println("Connection = " + connection);
		System.out.println("Accept = " + accept);
		System.out.println("<" + this.getClass().getSimpleName() + ">");
		System.out.println();
	}

	public HttpURLConnection getHttpURLConnection(HttpInvokerVo hiVo) throws IOException {
		URL url = getUrl(hiVo);

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		setTimeout(httpConn, hiVo.getTimeout());
		httpConn.setDoOutput(hiVo.isDoOutput());
		httpConn.setDoInput(hiVo.isDoInput());
		httpConn.setUseCaches(hiVo.isUseCaches());
		httpConn.setRequestMethod(hiVo.getMethod());

		httpConn.setRequestProperty("content-type", hiVo.getContentType());
		httpConn.setRequestProperty("accept", hiVo.getAccept());
		httpConn.setRequestProperty("user-agent", hiVo.getUserAgent());
		httpConn.setRequestProperty("connection", hiVo.getConnection());

		if ("GET".equals(hiVo.getMethod())) {
			return httpConn;
		}

//		if ("application/x-www-form-urlencoded".equals(hiVo.getContentType())) {
//			
//		}

		String body = hiVo.getBody();
		if (body == null || body.isEmpty()) {
			return httpConn;
		}

		httpConn.setRequestProperty("content-length", String.valueOf(body.length()));

		DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream());

//		wr.writeBytes(body);

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
		writer.write(body);
		writer.close();
		wr.close();

		wr.flush();
		wr.close();

		return httpConn;
	}

	private URL getUrl(HttpInvokerVo hiVo) throws MalformedURLException {
		if ("GET".equals(hiVo.getMethod()) && hiVo.getBody() != null) {
			return new URL(hiVo.getRequestUrl() + "?" + hiVo.getBody());
		}

		return new URL(hiVo.getRequestUrl());
	}

	public void setTimeout(HttpURLConnection httpConn, int timeoutWithSecond) {
		if (timeoutWithSecond <= 0) {
			return;
		}

		httpConn.setConnectTimeout(timeoutWithSecond * 1000);
	}

}
