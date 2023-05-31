package cl.forum.bts.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import cl.forum.bts.exception.ResponseException;
import cl.forum.bts.exception.StatusResponseEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "success", "retorno", "body", })
public class GenericResponse {

	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("return")
	private DetailResponse detailResponse;

	public GenericResponse(ResponseException e) {
		super();
		this.success = false;
		this.detailResponse = new DetailResponse(e.getStatusResponseEnum().getStatusCode(), e.getMessage());
	}

	public GenericResponse(Exception e) {
		super();
		this.success = false;
		this.detailResponse = new DetailResponse(StatusResponseEnum.INTERNAL_SERVER_ERROR.getStatusCode(),
				e.getMessage());
	}

	public GenericResponse() {
		super();
	}

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

}
