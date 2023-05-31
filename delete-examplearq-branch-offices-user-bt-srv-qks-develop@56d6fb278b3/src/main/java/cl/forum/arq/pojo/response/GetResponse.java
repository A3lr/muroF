package cl.forum.arq.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class GetResponse<T> {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "success", "return", "body" })
	@JsonProperty("success")
	private boolean success;
	@JsonProperty("return")
	private DetailResponse detailResponse;
	@JsonProperty("body")
	private T body;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public DetailResponse getDetailResponse() {
		return detailResponse;
	}

	public void setDetailResponse(DetailResponse detailResponse) {
		this.detailResponse = detailResponse;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}