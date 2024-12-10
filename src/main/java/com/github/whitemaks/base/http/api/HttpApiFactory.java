package com.github.whitemaks.base.http.api;

import com.github.whitemaks.base.http.api.wrappers.RestAssuredHttpApi;

public class HttpApiFactory {

	public static HttpApi create() {
		return new RestAssuredHttpApi();
	}

}
