package base.http.api;

import com.google.gson.Gson;

import java.io.File;

public final class RestResponse {
	private String contentType;
	private String body;
	private File file;

	private StatusCode status;
	private long time;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public <T> T getBodyByClass(Class<T> responseClass) {
		return new Gson().fromJson(body, responseClass);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
