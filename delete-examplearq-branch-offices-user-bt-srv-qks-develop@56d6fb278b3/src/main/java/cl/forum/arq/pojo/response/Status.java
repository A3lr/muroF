package cl.forum.arq.pojo.response;

public class Status {

	private String code;
	private String message;

	public Status() {
	}

	public Status(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public Status setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Status setMessage(String message) {
		this.message = message;
		return this;
	}
}