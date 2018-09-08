package io.github.pleuvoir.net;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkhttpUtil {
	
	private static final long DEFAULT_TIMEOUT_MILLIS = 35 * 1000L;

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


	static {
		System.setProperty("https.protocols", "TLSv1");
		System.setProperty("jsse.enableSNIExtension", "false");
	}
	
	
	private static OkHttpClient clientWithTimeout() {
		return new OkHttpClient().newBuilder().readTimeout(DEFAULT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).build();
	}
	
	public static String getAsString(String url) {
		try {
			Request okHttpRequest 		= new Request.Builder().url(url).build();
			OkHttpClient cookieClient 	= clientWithTimeout();
			Response response 			= cookieClient.newCall(okHttpRequest).execute();
			String body 				= response.body().string();
			return body;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T getAsDomain(String url,Class<T> domain) {
		try {
			Request okHttpRequest 		= new Request.Builder().url(url).build();
			OkHttpClient cookieClient 	= clientWithTimeout();
			Response response 			= cookieClient.newCall(okHttpRequest).execute();
			String body 				= response.body().string();
			return JSONObject.parseObject(body, domain);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> List<T> getAsDomainArrays(String url,Class<T> domain) {
		try {
			Request okHttpRequest 		= new Request.Builder().url(url).build();
			OkHttpClient cookieClient 	= clientWithTimeout();
			Response response 			= cookieClient.newCall(okHttpRequest).execute();
			String body 				= response.body().string();
			return JSONObject.parseArray(body, domain);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getAsStringWithCookies(String url, String cookies) {
		try {
			Request okHttpRequest 		= new Request.Builder().url(url).addHeader("Cookie", cookies).build();
			OkHttpClient cookieClient 	= clientWithTimeout();
			Response response 			= cookieClient.newCall(okHttpRequest).execute();
			String body 				= response.body().string();
			return body;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String postJson(String url, String json) {
		try {
			RequestBody requestBody 	= RequestBody.create(JSON, json);
			Request okHttpRequest		= new Request.Builder().url(url).post(requestBody).build();
			OkHttpClient cookieClient 	= clientWithTimeout();
			Response response 			= cookieClient.newCall(okHttpRequest).execute();
			String body 				= response.body().string();
			return body;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
