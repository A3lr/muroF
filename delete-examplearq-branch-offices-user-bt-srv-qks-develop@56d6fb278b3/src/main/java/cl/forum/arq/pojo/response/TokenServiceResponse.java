package cl.forum.arq.pojo.response;

public class TokenServiceResponse {

	private boolean error;
	private String errorMsg;
	private boolean active;
	private String token;

	public TokenServiceResponse() {
	}

	public TokenServiceResponse(boolean error, String errorMsg) {
		this.error = error;
		this.errorMsg = errorMsg;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}