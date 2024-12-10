package base.http.api.wrappers;

import base.http.api.HttpApi;
import base.http.api.RestResponse;
import base.http.api.StatusCode;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestAssuredHttpApi implements HttpApi {

	@Override
	public RestResponse sendGet(String url) {
		return sendGet(url, null, null);
	}

	@Override
	public RestResponse sendGet(String url, Map<String, String> headers) {
		return sendGet(url, null, headers);
	}

	@Override
	public RestResponse sendGet(String url, Object queryParams) {
		return sendGet(url, queryParams, null);
	}

	@Override
	public RestResponse sendGet(String url, Object queryParams, Map<String, String> headers) {
		return getRestResponse(
			getRestRequestSpecification()
				.params(convertObjectToMap(queryParams))
				.headers(headers == null ? new HashMap<>() : headers)
				.get(url)
		);
	}

	@Override
	public RestResponse sendPost(String url) {
		return sendPost(url, null, null);
	}

	@Override
	public RestResponse sendPost(String url, Map<String, String> headers) {
		return sendPost(url, null, headers);
	}

	@Override
	public RestResponse sendPost(String url, Object body) {
		return sendPost(url, body, null);
	}

	@Override
	public RestResponse sendPost(String url, Object body, Map<String, String> headers) {
		var specification = getRestRequestSpecification()
			.headers(headers == null ? new HashMap<>() : headers);

		if (body != null) {
			specification.body(new Gson().toJson(body))
				.contentType(ContentType.JSON);
		}

		return getRestResponse(specification.post(url));
	}

	@Override
	public RestResponse sendPut(String url) {
		return sendPut(url, null, null);
	}

	@Override
	public RestResponse sendPut(String url, Map<String, String> headers) {
		return sendPut(url, null, headers);
	}

	@Override
	public RestResponse sendPut(String url, Object body) {
		return sendPut(url, body, null);
	}

	@Override
	public RestResponse sendPut(String url, Object body, Map<String, String> headers) {
		var specification = getRestRequestSpecification()
			.headers(headers == null ? new HashMap<>() : headers);

		if (body != null) {
			specification.body(new Gson().toJson(body))
				.contentType(ContentType.JSON);
		}

		return getRestResponse(specification.put(url));
	}

	@Override
	public RestResponse sendDelete(String url) {
		return sendDelete(url, null, null);
	}

	@Override
	public RestResponse sendDelete(String url, Map<String, String> headers) {
		return sendDelete(url, null, headers);
	}

	@Override
	public RestResponse sendDelete(String url, Object body) {
		return sendDelete(url, body, null);
	}

	@Override
	public RestResponse sendDelete(String url, Object body, Map<String, String> headers) {
		var specification = getRestRequestSpecification()
			.headers(headers == null ? new HashMap<>() : headers);

		if (body != null) {
			specification.body(new Gson().toJson(body))
				.contentType(ContentType.JSON);
		}

		return getRestResponse(specification.delete(url));
	}

	private RequestSpecification getRestRequestSpecification() {
		return RestAssured.given().relaxedHTTPSValidation();
	}

	private Map<String, Object> convertObjectToMap(Object object) {
		var result = new HashMap<String, Object>();

		if (object == null) {
			return result;
		}

		var jsonObject = new Gson()
			.toJsonTree(object)
			.getAsJsonObject();

		for (var entry : jsonObject.entrySet()) {
			result.put(entry.getKey(), entry.getValue().getAsString());
		}

		return result;
	}

	private RestResponse getRestResponse(Response response) {
		var result = new RestResponse();

		result.setTime(response.getTime());
		result.setContentType(response.getContentType());
		result.setStatus(StatusCode.valueOf(response.getStatusCode()));

		if (response.getContentType().equalsIgnoreCase("application/octet-stream")) {
			var file = saveFileToTemp(response);
			result.setBody(String.format("The file has been downloaded in the following directory: %s", file.getAbsoluteFile()));
			result.setFile(file);
		} else {
			result.setBody(response.getBody().asString());
		}

		return result;
	}

	private File saveFileToTemp(Response response) {
		var tmpDir = System.getProperty("java.io.tmpdir");
		var fileName = response.getHeader("Content-Disposition").split("=")[1];

		var result = new File(String.format("%s%s-%s", tmpDir, Thread.currentThread().getName(), fileName));

		try (
			var inputStream = response.getBody().asInputStream();
			var fileOutputStream = new FileOutputStream(result)
		) {
			var buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		return result;
	}
}
