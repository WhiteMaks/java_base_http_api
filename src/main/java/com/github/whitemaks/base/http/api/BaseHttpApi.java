package com.github.whitemaks.base.http.api;

import java.util.Map;

public abstract class BaseHttpApi {
	private final HttpApi httpApi;

	public BaseHttpApi() {
		this(HttpApiFactory.create());
	}

	public BaseHttpApi(HttpApi httpApi) {
		this.httpApi = httpApi;
	}

	protected RestResponse sendGet(String url) {
		return httpApi.sendGet(url);
	}

	protected RestResponse sendGet(String url, Map<String, String> headers) {
		return httpApi.sendGet(url, headers);
	}

	protected RestResponse sendGet(String url, Object queryParams) {
		return httpApi.sendGet(url, queryParams);
	}

	protected RestResponse sendGet(String url, Object queryParams, Map<String, String> headers) {
		return httpApi.sendGet(url, queryParams, headers);
	}

	protected RestResponse sendPost(String url) {
		return httpApi.sendPost(url);
	}

	protected RestResponse sendPost(String url, Map<String, String> headers) {
		return httpApi.sendPost(url, headers);
	}

	protected RestResponse sendPost(String url, Object body) {
		return httpApi.sendPost(url, body);
	}

	protected RestResponse sendPost(String url, Object body, Map<String, String> headers) {
		return httpApi.sendPost(url, body, headers);
	}

	protected RestResponse sendPut(String url) {
		return httpApi.sendPut(url);
	}

	protected RestResponse sendPut(String url, Map<String, String> headers) {
		return httpApi.sendPut(url, headers);
	}

	protected RestResponse sendPut(String url, Object body) {
		return httpApi.sendPut(url, body);
	}

	protected RestResponse sendPut(String url, Object body, Map<String, String> headers) {
		return httpApi.sendPut(url, body, headers);
	}

	protected RestResponse sendDelete(String url) {
		return httpApi.sendDelete(url);
	}

	protected RestResponse sendDelete(String url, Map<String, String> headers) {
		return httpApi.sendDelete(url, headers);
	}

	protected RestResponse sendDelete(String url, Object body) {
		return httpApi.sendDelete(url, body);
	}

	protected RestResponse sendDelete(String url, Object body, Map<String, String> headers) {
		return httpApi.sendDelete(url, body, headers);
	}
}
