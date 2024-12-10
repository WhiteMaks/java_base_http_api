package com.github.whitemaks.base.http.api;

import java.util.Map;

public interface HttpApi {

	RestResponse sendGet(String url);
	RestResponse sendGet(String url, Map<String, String> headers);
	RestResponse sendGet(String url, Object queryParams);
	RestResponse sendGet(String url, Object queryParams, Map<String, String> headers);

	RestResponse sendPost(String url);
	RestResponse sendPost(String url, Map<String, String> headers);
	RestResponse sendPost(String url, Object body);
	RestResponse sendPost(String url, Object body, Map<String, String> headers);

	RestResponse sendPut(String url);
	RestResponse sendPut(String url, Map<String, String> headers);
	RestResponse sendPut(String url, Object body);
	RestResponse sendPut(String url, Object body, Map<String, String> headers);

	RestResponse sendDelete(String url);
	RestResponse sendDelete(String url, Map<String, String> headers);
	RestResponse sendDelete(String url, Object body);
	RestResponse sendDelete(String url, Object body, Map<String, String> headers);
}
