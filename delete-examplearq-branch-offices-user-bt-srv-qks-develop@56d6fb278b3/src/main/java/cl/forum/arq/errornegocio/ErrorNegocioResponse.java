package cl.forum.arq.errornegocio;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "retorno", "body", })
public class ErrorNegocioResponse {

	@JsonProperty("ErrorNegocio")
	private List<ErrorNegocioDetailResponse> errores;

	public List<ErrorNegocioDetailResponse> getErrores() {
		return errores;
	}

	public void setErrores(List<ErrorNegocioDetailResponse> errores) {
		this.errores = errores;
	}
}