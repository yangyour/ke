package cn.dblearn.dbblog.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import cn.dblearn.dbblog.common.exception.HttpRequestException;

/**
 * http客户端工具
 * @author developer001
 *
 */
public class HttpClient {
	private static CloseableHttpClient httpClient = null;
	public static final String CHARSET = "UTF-8";

	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000)
				.setSocketTimeout(15000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config)
				.build();
	}

	/**
	 * 获取httpClient
	 * @return
	 */
	public static CloseableHttpClient getHttpClient(){
		return httpClient;
	}

	public static String doGet(String url, Map<String, String> params) throws ParseException, UnsupportedEncodingException, HttpRequestException, IOException {
		return doGet(url, params, null,CHARSET);
	}

	public static String doGet(String url, Map<String, String> params,Map<String,String> headers) throws ParseException, UnsupportedEncodingException, HttpRequestException, IOException {
		return doGet(url, params, headers,CHARSET);
	}

	public static String doPost(String url, Map<String, String> params) throws ClientProtocolException, IOException, HttpRequestException {
		return doPost(url, params,null, CHARSET);
	}

	public static String doPost(String url, Map<String, String> params,Map<String,String> headers) {
		try {
			return doPost(url, params,headers, CHARSET);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String doProxyPost(String url, Map<String, String> params,
			HttpHost proxy) {
		return doProxyPost(url, params, CHARSET, proxy);
	}

	@SuppressWarnings("rawtypes")
	public static String doGet(String url, Map<String, String> params,
			Map<String,String> headers,String charset) throws ParseException, UnsupportedEncodingException, IOException,HttpRequestException {
		if (StringUtils.isBlank(url))
			return null;
		if ((params != null) && (!params.isEmpty())) {
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>(params.size());
			for (Map.Entry entry : params.entrySet()) {
				String value = (String) entry.getValue();
				if (value != null) {
					pairs.add(new BasicNameValuePair((String) entry
							.getKey(), value));
				}
			}
			url = url
					+ "?"
					+ EntityUtils.toString(new UrlEncodedFormEntity(pairs,
							charset));
		}
		HttpGet httpGet = new HttpGet(url);
		if(headers!=null&&!headers.isEmpty()){
			for (Map.Entry<String,String> entry : headers.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}
		return doGet(url,httpGet,charset);
	}

	public static String doGet(String url, String params,
			Map<String,String> headers,String charset) throws ClientProtocolException, IOException, HttpRequestException {
		if (StringUtils.isBlank(url))
			return null;

			if ((params != null) && (!params.isEmpty())) {
				url = url+ "?"+ params;
			}
			HttpGet httpGet = new HttpGet(url);
			if(headers!=null&&!headers.isEmpty()){
				for (Map.Entry<String,String> entry : headers.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						httpGet.addHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			return doGet(url,httpGet,charset);
	}

	public static String doGet(String url, HttpGet httpGet,String charset) throws ClientProtocolException, IOException,HttpRequestException {
		if (StringUtils.isBlank(url))
			return null;

		CloseableHttpResponse response = httpClient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			httpGet.abort();
			throw new HttpRequestException(statusCode,"HttpClient,error status code :"
					+ statusCode);
		}
		HttpEntity entity = response.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity, CHARSET);
		}
		EntityUtils.consume(entity);
		response.close();
		return result;
	}

	public static String doPost(String url, Map<String, String> params,
			Map<String,String> headers,String charset) throws ClientProtocolException, IOException, HttpRequestException {
		if (StringUtils.isBlank(url))
			return null;
			List<BasicNameValuePair> pairs = null;
			if ((params != null) && (!params.isEmpty())) {
				pairs = new ArrayList<BasicNameValuePair>(params.size());
				for (Map.Entry<String,String> entry : params.entrySet()) {
					String value =  entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair((String) entry
								.getKey(), value));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if(headers!=null&&!headers.isEmpty()){
				for (Map.Entry<String,String> entry : headers.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						httpPost.addHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			if ((pairs != null) && (pairs.size() > 0)) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
			}
			return doPost(httpPost, charset);
	}


	public static String doPost(String url, String params,
			Map<String,String> headers,String charset) throws ClientProtocolException, HttpRequestException, IOException {
		if (StringUtils.isBlank(url))
			return null;

		HttpPost httpPost = new HttpPost(url);
		if(headers!=null&&!headers.isEmpty()){
			for (Map.Entry<String,String> entry : headers.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		if(StringUtils.isNotEmpty(params)){
			StringEntity requestEntity = new StringEntity(params,CHARSET);
			requestEntity.setContentEncoding(CHARSET);
			httpPost.setEntity(requestEntity);
		}

		return doPost(httpPost, charset);
	}

	public static String doPost(HttpPost httpPost,String charset) throws ClientProtocolException, IOException,HttpRequestException {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new HttpRequestException(statusCode,"HttpClient,error status code :"
						+ statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
	}

	@SuppressWarnings("rawtypes")
	public static String doProxyPost(String url, Map<String, String> params,
			String charset, HttpHost proxy) {
		if (StringUtils.isBlank(url)) {
			return null;
		}

		CloseableHttpResponse response = null;
		try {
			List<BasicNameValuePair> pairs = null;
			if ((params != null) && (!params.isEmpty())) {
				pairs = new ArrayList<BasicNameValuePair>(params.size());
				for (Map.Entry entry : params.entrySet()) {
					String value = (String) entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair((String) entry
								.getKey(), value));
					}
				}
			}
			RequestConfig config = RequestConfig.custom().setProxy(proxy)
					.build();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(config);

			if ((pairs != null) && (pairs.size() > 0)) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}

			httpClient = HttpClientBuilder.create()
					.setDefaultRequestConfig(config).build();
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new HttpRequestException(statusCode,"HttpClient,error status code :"
						+ statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, CHARSET);
			}
			EntityUtils.consume(entity);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
