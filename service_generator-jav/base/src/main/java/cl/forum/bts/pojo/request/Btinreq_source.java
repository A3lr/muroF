package cl.forum.bts.pojo.request;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({

		"Canal", "Requerimiento", "Usuario", "Token", "Device"

})
@EntityScan
@JsonRootName("Btinreq")

public class Btinreq {

	@ApiModelProperty(value = "identificador de Aplicacion invocadora ", required = true, example = "1", position = 1)
	@NotNull(message = "El Canal es un atributo obligatorio")
	@JsonProperty("Canal")
	private String canal;
	@ApiModelProperty(value = "identificador de requerimiento", required = true, example = "1", position = 1)
	@JsonProperty("Requerimiento")
	private String requerimiento;

	@JsonProperty("Usuario")
	@NotNull(message = "El Usuario es un atributo obligatorio")
	@ApiModelProperty(value = "identificador de Usuario", required = true, example = "1", position = 1)
	private String usuario;
	

	@JsonProperty("Token")
	@ApiModelProperty(value = "Token de autorizacion de transacciones ", required = true, example = "1", position = 1)
	private String token;
	@JsonProperty("Device")
	@NotNull(message = "El Device es un atributo obligatorio")
	@ApiModelProperty(value = "Device asociado a transaccion ", required = true, example = "1", position = 1)
	private String device;



	public String getCanal() {
		return canal;
	}

	public String getRequerimiento() {
		return requerimiento;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getToken() {
		return token;
	}

	public String getDevice() {
		return device;
	}

	// Setter Methods

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public void setRequerimiento(String requerimiento) {
		this.requerimiento = requerimiento;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setDevice(String device) {
		this.device = device;
	}


}
