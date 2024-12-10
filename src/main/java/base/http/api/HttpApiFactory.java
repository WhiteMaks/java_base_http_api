package base.http.api;

import base.http.api.wrappers.RestAssuredHttpApi;

public class HttpApiFactory {

	public static HttpApi create() {
		return new RestAssuredHttpApi();
	}

}
